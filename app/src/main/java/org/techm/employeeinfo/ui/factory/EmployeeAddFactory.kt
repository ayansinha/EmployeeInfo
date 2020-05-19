package org.techm.employeeinfo.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.techm.employeeinfo.data.model.employeeadd.EmployeeAddCallBack
import org.techm.employeeinfo.ui.employee.viewmodel.EmployeeAddViewModel

/**
 * @class{EmployeeAddFactory}
 */

@Suppress("UNCHECKED_CAST")
class EmployeeAddFactory(private val listener: EmployeeAddCallBack) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeeAddViewModel::class.java)) {
            return EmployeeAddViewModel(listener) as T
        }
        throw IllegalArgumentException("Unknown class name")

    }
}