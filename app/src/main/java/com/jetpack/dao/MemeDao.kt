package com.jetpack.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jetpack.entity.MemeModel

@Dao
public interface MemeDao {


    @Query("Select * from MemeModel")
    suspend fun getAllMemes(): List<MemeModel>


    @Insert
    suspend fun insertMemes(memeList: ArrayList<MemeModel>)

    @Insert
    suspend fun insertMeme(meme: MemeModel)
}