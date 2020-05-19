package org.techm.employeeinfo.ui.employee.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_employee.*
import org.dtransform.techm.data.model.employeeadd.EmployeeAddCallBack
import org.techm.employeeinfo.R
import org.techm.employeeinfo.databinding.ActivityAddEmployeeBinding
import org.techm.employeeinfo.ui.employee.viewmodel.EmployeeAddViewModel
import org.techm.employeeinfo.ui.factory.EmployeeAddFactory

/**
 *@class{EmployeeAddActivity}
 */

@SuppressLint("Registered")
class EmployeeAddActivity: AppCompatActivity() , EmployeeAddCallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        val activityAddEmployeeBinding = DataBindingUtil.setContentView<ActivityAddEmployeeBinding>(this , R.layout.activity_add_employee)

        activityAddEmployeeBinding.addInfo = ViewModelProviders.of(this, EmployeeAddFactory(this))
            .get(EmployeeAddViewModel::class.java)
        setSupportActionBar(findViewById(R.id.toolBar))


    }

    override fun onSuccess(message: String) {
        //toastShort(message)
        hideDialog()
        editTextEmpName.setText("")
        editTextEmpSalary.setText("")
        editTextEmpAge.setText("")
    }

    override fun onFailure(message: String) {
        //toastShort(message)
        hideDialog()
        editTextEmpName.setText("")
        editTextEmpSalary.setText("")
        editTextEmpAge.setText("")
    }

    override fun showDialog() {
        progressBarCreateEmployee.visibility = View.VISIBLE
    }

    override fun hideDialog() {
        progressBarCreateEmployee.visibility = View.GONE
    }

}