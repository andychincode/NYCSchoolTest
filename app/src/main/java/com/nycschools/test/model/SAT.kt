package com.nycschools.test.model

import com.google.gson.annotations.SerializedName

data class SAT(
    @SerializedName("dbn")
    var dbn: String?,
    @SerializedName("school_name")
    var name: String?,
    @SerializedName("num_of_sat_test_takers")
    var numOfSatTestTakers: String?,
    @SerializedName("sat_critical_reading_avg_score")
    var satCriticalReadingAvgScore: String?,
    @SerializedName("sat_math_avg_score")
    var satMathAvgScore: String?,
    @SerializedName("sat_writing_avg_score")
    var satWritingAvgScore: String?
)
