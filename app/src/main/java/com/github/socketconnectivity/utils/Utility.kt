package com.github.socketconnectivity.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.support.design.widget.Snackbar
import android.view.View

object Utility {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun displaySnackBar(view: View, msg: String) {
        val snackBar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG)
        snackBar.show()
    }
}