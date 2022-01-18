package com.jetpack

object MemeLocalRepository {


    suspend fun callGetAllMemes(memeDataBase: MemeDataBase) =
        memeDataBase.memeDao().getAllMemes()
}

