package com.sample.data.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request()
        Log.d("Interceptor", newRequest.url().toString())

        return chain.proceed(newRequest)
    }

}