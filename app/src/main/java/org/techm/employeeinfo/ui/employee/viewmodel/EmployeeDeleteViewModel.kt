package org.techm.employeeinfo.ui.employee.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.dtransform.techm.data.network.ServiceBuilder
import org.techm.employeeinfo.util.isConnection

/**
 *@class{EmployeeDeleteViewModel}
 */

class EmployeeDeleteViewModel(private  var context: Context): ViewModel() {

    var status: String = " "
    var message: String = " "

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    var statusLiveData = MutableLiveData<String> ()
    var messageLiveData = MutableLiveData<String>()
    var loadingError = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()


    private fun deleteEmployeeById(index: Int) {
        if (context.isConnection()) {
            compositeDisposable.add(
                ServiceBuilder.buildService().deleteEmployeeByID(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        Log.e("result status -> ", "${result.status}")
                        Log.e("result message ->", "${result.message}")
                        onSuccess(result.message)
                    },
                    { err ->
                        err.message?.let {
                            onError(index, err.message.toString())
                        }
                    }
                ))
        }else {

        }

    }

    private fun onSuccess(message: String) {

    }

    private fun onError(position: Int, message: String) {


    }

}