package org.techm.employeeinfo.data.model.employeeadd


/**
 * @class{EmployeeAddCallBack}
 */

interface EmployeeAddCallBack {
    fun onSuccess(message: String)
    fun onFailure(message: String)
    fun showDialog()
    fun hideDialog()
}