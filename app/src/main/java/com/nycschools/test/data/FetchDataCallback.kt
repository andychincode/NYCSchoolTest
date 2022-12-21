package com.nycschools.test.data

interface FetchDataCallback<T> {
    fun onSuccess(data: T?)
    fun onError(strError: String)
}