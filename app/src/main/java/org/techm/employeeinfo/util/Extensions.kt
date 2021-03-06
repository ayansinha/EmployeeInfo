package org.techm.employeeinfo.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


/**
 * extension function for toast short
 */
fun Context.toastShort(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this , message , duration).show()
}

/**
 * extension function for toast long
 */
fun Context.toastLong(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this , message , duration).show()
}


/**
 * extension function for snack-bar
 */
fun View.showSnackBarView(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this , message , duration).show()
}

/**
 * checks internet connection
 */
fun Context.isConnection(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
}

/**
 * one activity to another
 */
inline fun <reified T : Activity> Context.startNewActivity() {
    startActivity(Intent(this , T::class.java))
}


