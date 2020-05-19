package org.techm.employeeinfo.ui.employee.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.dtransform.techm.data.network.ServiceBuilder
import org.techm.employeeinfo.data.model.employeedisplay.DataModel
import org.techm.employeeinfo.data.model.employeedisplay.EmployeeDataModel
import timber.log.Timber

/**
 *
 */

class EmployeeListViewModel: ViewModel() {
    private val employeeList = MutableLiveData<List<DataModel>>()
    private val compositeDisposable = CompositeDisposable()


    private fun loadEmployeeData() {
        compositeDisposable.add(
            ServiceBuilder.buildService().getEmployeesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            //.subscribe { result -> onSuccessResponse(result) } , {t -> onErrorResponse(t)})
            .subscribe{res ->
                //employeeList = res.data
                Timber.d("response -> $res")
                //adapter = EmployeeListAdapter(res.data, this@EmployeeListActivity)
                //listEmployee = res.data
                onSuccessResponse(response = res)})
    }

    private fun onSuccessResponse(response: EmployeeDataModel) {
        Timber.d("response -> $response")
        Log.d("response -> " , " $response")
        //adapter = EmployeeListAdapter(response.data , this@EmployeeListActivity)
        //adapter.addData(response.data)
        //adapter.notifyDataSetChanged()

    }

    private fun onErrorResponse(t: Throwable) {
        Timber.d("response -> ${t.message}")
        Log.d("response -> " , " ${t.message}")
    }
}