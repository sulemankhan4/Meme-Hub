package com.jetpack

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jetpack.dao.MemeDao
import com.jetpack.entity.MemeModel

@Database(entities = [MemeModel::class], version = 1, exportSchema = false)
abstract class MemeDataBase : RoomDatabase() {

    abstract fun memeDao(): MemeDao

    companion object {
        @Volatile
        private var instance: MemeDataBase? = null

        fun getInstance(context: Context): MemeDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, MemeDataBase::class.java, "memedb")
                .build()
    }
}