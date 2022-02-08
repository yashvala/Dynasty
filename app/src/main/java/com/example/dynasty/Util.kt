package com.example.dynasty

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.internal.Internal.instance

public fun hasNetwork(context: Context): Boolean {
    return instance.checkIfHasNetwork(context)
}

public fun Any.checkIfHasNetwork(context: Context): Boolean {

    val cm =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val networkInfo = cm!!.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

