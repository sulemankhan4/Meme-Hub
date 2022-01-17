package com.jetpack.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.jetpack.workmanager.GetDataWorkManager

class BaseApplication : Application() {

    companion object {
        lateinit var appInstance: BaseApplication

        fun getContext() = appInstance
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        val oneTimeRequest = OneTimeWorkRequestBuilder<GetDataWorkManager>()
            .addTag("get_data")
            .build()
        WorkManager.getInstance(this).enqueue(oneTimeRequest)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}