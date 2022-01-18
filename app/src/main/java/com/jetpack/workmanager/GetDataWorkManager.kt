package com.jetpack.workmanager

import android.content.Context
import android.text.TextUtils
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jetpack.MemeDataBase
import com.jetpack.api.APIRequestHandler
import com.jetpack.utils.ImageUtils

class GetDataWorkManager(
    var context: Context,
    var workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {


    override suspend fun doWork(): Result {
        val result = APIRequestHandler.instance?.api.callGetMemeList()
        if (result?.success) {
            val memeList = result?.data?.memes ?: ArrayList()
            for (meme in memeList) {
                if (!TextUtils.isEmpty(meme.url)) {
                    ImageUtils.preloadImage(context, meme.url ?: "")
                }
            }
            MemeDataBase.getInstance(context).memeDao().insertMemes(memeList)
        }
        return Result.success()

    }
}