package com.jetpack.api

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jetpack.base.BaseApplication
import com.jetpack.const.AppConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIRequestHandler private constructor(timeout: Int, base_url: String) {
    private val retrofit: Retrofit
    val api: APICommonInterface
        get() = retrofit.create(APICommonInterface::class.java)

    companion object {
        private const val DEFAULT_TIMEOUT = 30000 // millis
        fun getInstance(timeout: Int): APIRequestHandler {
            return APIRequestHandler(timeout, AppConstants.BASE_URL)
        }

        fun getInstance(base_url: String): APIRequestHandler {
            return APIRequestHandler(DEFAULT_TIMEOUT, base_url)
        }

        fun getInstance(base_url: String, timeout: Int): APIRequestHandler {
            return APIRequestHandler(timeout, base_url)
        }

        val instance: APIRequestHandler
            get() = APIRequestHandler(DEFAULT_TIMEOUT, AppConstants.BASE_URL)
    }

    init {
        val httpClient = OkHttpClient.Builder()
        httpClient.writeTimeout(timeout.toLong(), TimeUnit.MILLISECONDS)
        httpClient.readTimeout(timeout.toLong(), TimeUnit.MILLISECONDS)
        httpClient.connectTimeout(timeout.toLong(), TimeUnit.MILLISECONDS)
        httpClient.addInterceptor(ChuckerInterceptor(BaseApplication.getContext()))

        retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}