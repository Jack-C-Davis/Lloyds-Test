package com.jackdv.earthquakes.util.ConnectivityUtils

/**
 * Simple interface that contains online/offline state indicator
 */
interface OnlineChecker {
    fun isOnline(): Boolean
}