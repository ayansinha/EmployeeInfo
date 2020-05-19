package org.techm.employeeinfo.data.model.employeeadd

import android.text.TextUtils
import androidx.databinding.BaseObservable

/**
 * @class{EmployeeAddInfo}
 */

data class EmployeeAddInfo(private var empName: String ,
                      private var empSalary: String ,
                      private var empAge: String): BaseObservable() {


    val isInfoValid: Boolean
    get() = (!TextUtils.isEmpty(empName))
            && (!TextUtils.isEmpty(empSalary))

    fun getEmpName(): String {
        return empName
    }

    fun getEmpSalary(): String {
        return empSalary
    }

    fun getEmpAge(): String {
        return empAge
    }

    fun setEmpName(name: String){
        this.empName = name
    }

    fun setEmpSalary(salary: String){
        this.empSalary = salary
    }

    fun setEmpAge(age: String){
        this.empAge = age
    }





}