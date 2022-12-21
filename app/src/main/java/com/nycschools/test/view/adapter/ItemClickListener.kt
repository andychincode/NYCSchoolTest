package com.nycschools.test.view.adapter

import com.nycschools.test.model.HighSchool

interface ItemClickListener {
    fun onItemClicked(school: HighSchool)
}