package org.techm.employeeinfo.data.model.employeedisplay

import com.google.gson.annotations.SerializedName

data class EmployeeDataModel(
    @SerializedName("data")
    val `data`: ArrayList<DataModel>,

    @SerializedName("status")
    val status: String
)