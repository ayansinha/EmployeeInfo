package org.techm.employeeinfo.data.model.employeedisplay

import com.google.gson.annotations.SerializedName

/**
 * @class{DataModel}
 */
data class DataModel(
    @SerializedName("employee_age")
    val employee_age: String,

    @SerializedName("employee_name")
    val employee_name: String,

    @SerializedName("employee_salary")
    val employee_salary: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("profile_image")
    val profile_image: String
)