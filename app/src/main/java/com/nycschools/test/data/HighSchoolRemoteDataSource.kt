package com.nycschools.test.data

import com.nycschools.test.model.HighSchool
import com.nycschools.test.model.SAT
import com.nycschools.test.network.ApiClient
import io.reactivex.rxjava3.core.Observable

class HighSchoolRemoteDataSource(apiClient: ApiClient): HighSchoolDataSource {
    private lateinit var fetchHighSchools: Observable<List<HighSchool>>
    private lateinit var fetchSATs: Observable<List<SAT>>

    private val service = apiClient.build()

    override fun retrieveHighSchools(callback: FetchDataCallback<List<HighSchool>>) {
        fetchHighSchools = service.getSchools()
        fetchHighSchools.subscribe(
            { value -> callback.onSuccess(value) },
            { error -> error.message?.let { callback.onError(it) } }
        )
    }

    override fun retrieveSATs(callback: FetchDataCallback<List<SAT>>) {
        fetchSATs = service.getSATs()
        fetchSATs.subscribe(
            { value -> callback.onSuccess(value) },
            { error -> error.message?.let { callback.onError(it) } }
        )
    }
}