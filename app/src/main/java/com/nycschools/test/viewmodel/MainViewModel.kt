package com.nycschools.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nycschools.test.data.FetchDataCallback
import com.nycschools.test.data.HighSchoolRepository
import com.nycschools.test.model.HighSchool
import com.nycschools.test.model.SAT

class MainViewModel(private val repository: HighSchoolRepository): ViewModel() {
    private val _schools = MutableLiveData<List<HighSchool>>()
    val schools: LiveData<List<HighSchool>> = _schools

    private val _sats = MutableLiveData<List<SAT>>()
    val sats: LiveData<List<SAT>> = _sats

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadHighSchools() {
        repository.fetchHighSchools(object : FetchDataCallback<List<HighSchool>> {
            override fun onSuccess(data: List<HighSchool>?) {
                data.let {
                    _schools.postValue(it)
                }
            }

            override fun onError(strError: String) {
                _error.postValue(strError)
            }
        })
    }

    fun loadSATs() {
        repository.fetchSATs(object : FetchDataCallback<List<SAT>> {
            override fun onSuccess(data: List<SAT>?) {
                data.let {
                    _sats.postValue(it)
                }
            }

            override fun onError(strError: String) {
                _error.postValue(strError)
            }
        })
    }
}