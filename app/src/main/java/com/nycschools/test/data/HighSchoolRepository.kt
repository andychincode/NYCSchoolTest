package com.nycschools.test.data

import com.nycschools.test.model.HighSchool
import com.nycschools.test.model.SAT

class HighSchoolRepository(private val dataSource: HighSchoolDataSource) {
    fun fetchHighSchools(callback: FetchDataCallback<List<HighSchool>>) {
        dataSource.retrieveHighSchools(callback)
    }

    fun fetchSATs(callback: FetchDataCallback<List<SAT>>) {
        dataSource.retrieveSATs(callback)
    }
}