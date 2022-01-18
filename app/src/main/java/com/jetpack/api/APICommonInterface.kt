package com.jetpack.api

import com.jetpack.const.AppConstants
import com.jetpack.entity.MemeModel
import com.jetpack.entity.MemeResponseModel
import retrofit2.http.GET

interface APICommonInterface {

    @GET(AppConstants.GET_MEME)
    suspend fun callGetMemeList(
    ): MemeResponseModel


    @GET(AppConstants.GET_MEME_FROM_MEMEGEN)
    suspend fun callGetMemeFromMemeGen(): ArrayList<MemeModel>?

}