package com.jetpack.api

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jetpack.const.AppConstants
import com.jetpack.base.BaseApplication
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIRequestHandler private constructor() {
    private val retrofit: Retrofit
    val api: APICommonInterface
        get() = retrofit.create(APICommonInterface::class.java)

    companion object {
        private var mInstance: APIRequestHandler? = null

        val instance: APIRequestHandler?
            get() {
                if (mInstance == null) {
                    mInstance = APIRequestHandler()
                }
                return mInstance
            }
    }

    init {
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(ChuckerInterceptor(BaseApplication.getContext()))
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}