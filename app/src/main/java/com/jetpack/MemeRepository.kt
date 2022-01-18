package com.jetpack

object MemeRepository {


    suspend fun getAllMemes(memeDataBase: MemeDataBase) =
        MemeLocalRepository.callGetAllMemes(memeDataBase)

    suspend fun callGetMemeFromMemeGen() = MemeRemoteRepository.callGetMemeFromMemeGen()

}