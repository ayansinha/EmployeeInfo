package org.dtransform.techm.data.model.employeeadd


/**
 *
 */

interface EmployeeAddCallBack {
    fun onSuccess(message: String)
    fun onFailure(message: String)
    fun showDialog()
    fun hideDialog()
}