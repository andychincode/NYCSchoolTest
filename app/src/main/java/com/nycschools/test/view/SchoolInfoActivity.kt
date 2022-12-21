package com.nycschools.test.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nycschools.test.R
import com.nycschools.test.databinding.ActivitySchoolInfoBinding
import com.nycschools.test.di.Injection
import com.nycschools.test.model.HighSchool
import com.nycschools.test.model.SAT
import com.nycschools.test.model.SchoolInfoItem
import com.nycschools.test.utils.Define
import com.nycschools.test.view.adapter.SchoolInfoListAdapter
import com.nycschools.test.viewmodel.MainViewModel

class SchoolInfoActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel               // viewmodel variable
    private lateinit var binding: ActivitySchoolInfoBinding     // databinding variable
    private lateinit var adapter: SchoolInfoListAdapter         // info list adapter

    private var school: HighSchool? = null      // NYCHighSchool data that was selected in previous list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_school_info)

        // load extra for NYCHighSchool data
        school = intent.getSerializableExtra(Define.EXTRA_SCHOOL) as? HighSchool

        school.let {
            binding.progressVisible = true      // show progress while load all SAT data
            setupViewModel()
            setupUI()

            viewModel.loadSATs()                // load all SAT data
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupUI() {
        this.title = school?.name ?: ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = SchoolInfoListAdapter(emptyList())
        binding.infoListAdapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory()) [MainViewModel::class.java]
        viewModel.sats.observe(this, onLoadSATsObserver)
        viewModel.error.observe(this, onErrorObserver)
    }

    // observer for loading of SAT data
    private val onLoadSATsObserver = Observer<List<SAT>> {
        binding.progressVisible = false     // hide progress

        val sats: MutableList<SAT> = mutableListOf()
        // fetch SATs for current NYCHighSchool data with [dbn] field
        for (sat in it) {
            if (sat.dbn.equals(school?.dbn)) {
                sats.add(sat)
            }
        }

        updateSchoolInfo(sats)
    }

    // observer for checking error
    private val onErrorObserver = Observer<String> {
        binding.progressVisible = false     // hide progress
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    private fun updateSchoolInfo(sats: List<SAT>) {
        val items = mutableListOf<SchoolInfoItem>()

        // add info items with NYCHighSchool data
        items.add(SchoolInfoItem(Define.TITLE_NAME, school?.name))
        items.add(SchoolInfoItem(Define.TITLE_OVERVIEW, school?.overview))
        items.add(SchoolInfoItem(Define.TITLE_LOCATION, school?.location))
        items.add(SchoolInfoItem(Define.TITLE_PHONE, school?.phoneNumber))
        items.add(SchoolInfoItem(Define.TITLE_FAX, school?.faxNumber))
        items.add(SchoolInfoItem(Define.TITLE_EMAIL, school?.email))
        items.add(SchoolInfoItem(Define.TITLE_WEBSITE, school?.website))
        items.add(SchoolInfoItem(Define.TITLE_TOTAL_STUDENTS, "${school?.totalStudents}"))
        items.add(SchoolInfoItem(Define.TITLE_LATITUDE, "${school?.latitude}"))
        items.add(SchoolInfoItem(Define.TITLE_LONGITUDE, "${school?.longitude}"))

        // loop SAT data for current NYCHighSchool data
        for (sat in sats) {
            items.add(SchoolInfoItem(Define.TITLE_SAT_TEST_TAKERS, "${sat.numOfSatTestTakers}"))
            items.add(SchoolInfoItem(Define.TITLE_SAT_READING_AVG_SCORE, "${sat.satCriticalReadingAvgScore}"))
            items.add(SchoolInfoItem(Define.TITLE_SAT_MATH_AVG_SCORE, "${sat.satMathAvgScore}"))
            items.add(SchoolInfoItem(Define.TITLE_SAT_WRITING_AVG_SCORE, "${sat.satWritingAvgScore}"))
        }

        adapter.update(items)
    }
}