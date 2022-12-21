package com.nycschools.test.di

import androidx.lifecycle.ViewModelProvider
import com.nycschools.test.data.HighSchoolDataSource
import com.nycschools.test.data.HighSchoolRemoteDataSource
import com.nycschools.test.data.HighSchoolRepository
import com.nycschools.test.network.ApiClient
import com.nycschools.test.viewmodel.ViewModelFactory

object Injection {
    private val dataSource: HighSchoolDataSource = HighSchoolRemoteDataSource(ApiClient)
    private val repository = HighSchoolRepository(dataSource)
    private val viewModelFactory = ViewModelFactory(repository)

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return viewModelFactory
    }
}