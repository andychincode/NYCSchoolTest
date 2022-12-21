package com.nycschools.test.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.nycschools.test.R
import com.nycschools.test.databinding.ActivityMainBinding
import com.nycschools.test.di.Injection
import com.nycschools.test.model.HighSchool
import com.nycschools.test.view.adapter.SchoolListAdapter
import com.nycschools.test.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel           // viewmodel variable
    private lateinit var adapter: SchoolListAdapter         // NYCHighSchool list adapter
    private lateinit var binding: ActivityMainBinding       // databinding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // checking network connection
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {    // network connected
                    runOnUiThread {
                        print(getString(R.string.network_connected))

                        binding.progressVisible = true      // show progress while load all NYCHighSchool data
                        viewModel.loadHighSchools()         // load all NYCHighSchool data
                    }
                }

                override fun onLost(network: Network) {     // network connection fail
                    Toast.makeText(this@MainActivity, getString(R.string.network_unavailable), Toast.LENGTH_SHORT).show()
                }
            }
        )

        setupViewModel()
        setupUI()
    }

    private fun setupUI() {
        adapter = SchoolListAdapter(this, emptyList())
        binding.listAdapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory()) [MainViewModel::class.java]
        viewModel.schools.observe(this, onLoadSchoolsObserver)
        viewModel.error.observe(this, onErrorObserver)
    }

    private val onLoadSchoolsObserver = Observer<List<HighSchool>> {
        binding.progressVisible = false     // hide progress
        if (it.isNotEmpty()) {
            adapter.update(it)
        } else {
            adapter.update(emptyList())
        }
    }

    private val onErrorObserver = Observer<String> {
        binding.progressVisible = false     // hide progress
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }
}