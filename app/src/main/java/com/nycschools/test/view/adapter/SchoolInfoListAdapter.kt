package com.nycschools.test.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nycschools.test.BR
import com.nycschools.test.R
import com.nycschools.test.databinding.SchoolInfoItemBinding
import com.nycschools.test.model.SchoolInfoItem

class SchoolInfoListAdapter(private var items: List<SchoolInfoItem>) : RecyclerView.Adapter<SchoolInfoListAdapter.ViewHolder>() {

    class ViewHolder(binding: SchoolInfoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val itemRowBinding: SchoolInfoItemBinding = binding

        fun bind(item: SchoolInfoItem) {
            itemRowBinding.setVariable(BR.schoolInfoItem, item)     // set variable for databinding
            itemRowBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<SchoolInfoItemBinding>(LayoutInflater.from(parent.context), R.layout.school_info_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val infoItem = items[position]
        holder.bind(infoItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(items: List<SchoolInfoItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}