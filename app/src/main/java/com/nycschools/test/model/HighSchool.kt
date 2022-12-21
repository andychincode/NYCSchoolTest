package com.nycschools.test.model

import com.google.gson.annotations.SerializedName

data class HighSchool(
    @SerializedName("dbn")
    var dbn: String?,
    @SerializedName("school_name")
    var name: String?,
    @SerializedName("boro")
    var boro: String?,
    @SerializedName("overview_paragraph")
    var overview: String?,
    @SerializedName("school_10th_seats")
    var seats: Int,
    @SerializedName("ell_programs")
    var ellPrograms: String?,
    @SerializedName("language_classes")
    var languageClasses: String?,
    @SerializedName("advancedplacement_courses")
    var advancedplacementCourses: String?,
    @SerializedName("diplomaendorsements")
    var diplomaendorsements: String?,
    @SerializedName("neighborhood")
    var neighborhood: String?,
    @SerializedName("building_code")
    var buildingCode: String?,
    @SerializedName("location")
    var location: String?,
    @SerializedName("phone_number")
    var phoneNumber: String?,
    @SerializedName("fax_number")
    var faxNumber: String?,
    @SerializedName("school_email")
    var email: String?,
    @SerializedName("website")
    var website: String?,
    @SerializedName("subway")
    var subway: String?,
    @SerializedName("bus")
    var bus: String?,
    @SerializedName("grades2018")
    var grades2018: String?,
    @SerializedName("finalgrades")
    var finalgrades: String?,
    @SerializedName("total_students")
    var totalStudents: String?,
    @SerializedName("start_time")
    var startTime: String?,
    @SerializedName("end_time")
    var endTime: String?,
    @SerializedName("addtl_info1")
    var addtlInfo1: String?,
    @SerializedName("extracurricular_activities")
    var extracurricularActivities: String?,
    @SerializedName("psal_sports_boys")
    var psalSportsBoys: String?,
    @SerializedName("psal_sports_girls")
    var psalSportsGirls: String?,
    @SerializedName("graduation_rate")
    var graduationRate: Double,
    @SerializedName("attendance_rate")
    var attendanceRate: Double,
    @SerializedName("pct_stu_enough_variety")
    var pctStuEnoughVariety: Double,
    @SerializedName("college_career_rate")
    var collegeCareerRate: Double?,
    @SerializedName("pct_stu_safe")
    var pctStuSafe: Double?,
    @SerializedName("primary_address_line_1")
    var primaryAddressLine1: String?,
    @SerializedName("city")
    var city: String?,
    @SerializedName("zip")
    var zip: String?,
    @SerializedName("state_code")
    var stateCode: String?,
    @SerializedName("latitude")
    var latitude: Double,
    @SerializedName("longitude")
    var longitude: Double
) : java.io.Serializable
