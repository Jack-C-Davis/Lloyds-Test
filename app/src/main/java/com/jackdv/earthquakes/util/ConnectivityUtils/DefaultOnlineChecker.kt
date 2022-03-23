package com.jackdv.earthquakes.util.ConnectivityUtils

import android.net.ConnectivityManager

/**
 * Custom OnlineChecker
 */
class DefaultOnlineChecker(private val connectivityManager: ConnectivityManager) : OnlineChecker {
    override fun isOnline(): Boolean {
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}