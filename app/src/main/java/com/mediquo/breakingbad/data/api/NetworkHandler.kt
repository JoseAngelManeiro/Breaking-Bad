package com.mediquo.breakingbad.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkHandler(private val context: Context) {

  fun isConnected(): Boolean {
    val connectivityManager = context
      .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      val network = connectivityManager.activeNetwork ?: return false
      val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
      return when {
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
      }
    } else {
      val network = connectivityManager.activeNetworkInfo ?: return false
      return network.isConnected
    }
  }
}
