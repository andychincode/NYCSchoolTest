package com.nycschools.test.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nycschools.test.BR
import com.nycschools.test.R
import com.nycschools.test.databinding.SchoolItemBinding
import com.nycschools.test.model.HighSchool
import com.nycschools.test.utils.Define
import com.nycschools.test.view.SchoolInfoActivity


class SchoolListAdapter(private var context: Context, private var schools: List<HighSchool>): RecyclerView.Adapter<SchoolListAdapter.ViewHolder>(), ItemClickListener {

    class ViewHolder(binding: SchoolItemBinding): RecyclerView.ViewHolder(binding.root) {
        val itemRowBinding: SchoolItemBinding = binding

        fun bind(school: HighSchool) {
            itemRowBinding.setVariable(BR.schoolModel, school)      // set variable for databinding
            itemRowBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<SchoolItemBinding>(LayoutInflater.from(parent.context), R.layout.school_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val school = schools[position]
        holder.bind(school)
        holder.itemRowBinding.itemClickListener = this
    }

    override fun getItemCount(): Int {
        return schools.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(schools: List<HighSchool>) {
        this.schools = schools
        notifyDataSetChanged()
    }

    override fun onItemClicked(school: HighSchool) {
        val intent = Intent(context, SchoolInfoActivity::class.java)
        intent.putExtra(Define.EXTRA_SCHOOL, school)
        context.startActivity(intent)
    }
}