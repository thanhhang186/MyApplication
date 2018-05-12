package com.example.nhyml.myapplication

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class NetworkAvailable {
    fun isNetWorkAvailable(context: Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if(networkInfo!=null && networkInfo.isAvailable){
            return true
        } else {
            return false
        }
    }
}