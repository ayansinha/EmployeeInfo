package org.techm.employeeinfo.ui.employee.viewmodel

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.dtransform.techm.data.model.employeeadd.EmployeeAddCallBack
import org.dtransform.techm.data.network.ServiceBuilder
import org.techm.employeeinfo.data.model.employeeadd.EmployeeAddInfo
import org.techm.employeeinfo.util.Constants
import org.techm.employeeinfo.util.showSnackBarView
import timber.log.Timber

/**
 *@class{EmployeeAddViewModel}
 */

class EmployeeAddViewModel(private val listener: EmployeeAddCallBack) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val empInfo: EmployeeAddInfo

    init {
        this.empInfo = EmployeeAddInfo("", "", "")
    }

    val nameTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                empInfo.setEmpName(s.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        }

    val salaryTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                empInfo.setEmpSalary(s.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        }

    val ageTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                empInfo.setEmpAge(s.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        }

    fun createEmployee(view: View) {
        Timber.e("name -> ${empInfo.getEmpName()}")
        Timber.e("salary -> ${empInfo.getEmpSalary()}")
        Timber.e("age -> ${empInfo.getEmpAge()}")
        Log.e("name:", "${empInfo.getEmpName()}")
        Log.e("salary:", "${empInfo.getEmpSalary()}")
        Log.e("age:", "${empInfo.getEmpAge()}")

        listener.showDialog()
        if (empInfo.isInfoValid) {

            compositeDisposable.add(ServiceBuilder.buildService().addEmployee(empInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ result -> result.let {
                    listener.onSuccess(it.status)
                    view.showSnackBarView(it.status)
                }},
                    {throwable -> throwable.message?.let { listener.onFailure(it) } }))

        } else {
            listener.hideDialog()
            view.showSnackBarView(Constants.ERROR_MSG)
        }

        nameTextWatcher.afterTextChanged(Editable.Factory.getInstance().newEditable(""))
    }
}