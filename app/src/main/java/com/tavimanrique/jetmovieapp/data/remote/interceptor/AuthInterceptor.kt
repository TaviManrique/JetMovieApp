package com.tavimanrique.jetmovieapp.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiRequestInterceptor(
    private val bearerToken: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $bearerToken")
            .addHeader("accept", "application/json")
            .build()
        return chain.proceed(request)
    }
}
