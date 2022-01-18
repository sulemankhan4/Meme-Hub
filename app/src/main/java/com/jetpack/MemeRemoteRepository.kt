package com.jetpack

import com.jetpack.api.APIRequestHandler
import com.jetpack.const.AppConstants

object MemeRemoteRepository {


    suspend fun callGetMemeFromMemeGen() =
        APIRequestHandler.getInstance(AppConstants.MEMEGEN_BASE_URL).api.callGetMemeFromMemeGen()
}