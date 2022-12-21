package com.nycschools.test.data

import com.nycschools.test.model.HighSchool
import com.nycschools.test.model.SAT

interface HighSchoolDataSource {
    fun retrieveHighSchools(callback: FetchDataCallback<List<HighSchool>>)
    fun retrieveSATs(callback: FetchDataCallback<List<SAT>>)
}