package org.techm.employeeinfo.data.network


import io.reactivex.rxjava3.core.*
import org.techm.employeeinfo.data.model.employeeadd.EmployeeAddData
import org.techm.employeeinfo.data.model.employeeadd.EmployeeAddInfo
import org.techm.employeeinfo.data.model.employeedelete.EmployeeDeleteData
import org.techm.employeeinfo.data.model.employeedisplay.EmployeeDataModel
import retrofit2.http.*

/**
 * @interface{APIService}
 */
interface APIService {

    @GET("employees")
    fun getEmployeesList(): Observable<EmployeeDataModel>

    @POST("create")
    fun addEmployee(@Body addInfo: EmployeeAddInfo): Single<EmployeeAddData>

    @DELETE("delete/{id}")
    fun deleteEmployeeByID(@Path("id") id:Int): Single<EmployeeDeleteData>
}