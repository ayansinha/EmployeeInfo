package org.dtransform.techm.data.network


import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.techm.employeeinfo.util.Constants
import org.techm.employeeinfo.data.network.APIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @class{singleton ServiceBuilder}
 */

object ServiceBuilder {

    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()
        .create(APIService::class.java)

    fun buildService(): APIService {
        return retrofit
    }
}