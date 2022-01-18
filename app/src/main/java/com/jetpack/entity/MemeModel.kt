package com.jetpack.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class MemeResponseModel(
    var success: Boolean,
    var data: MemeDataModel? = null
)

data class MemeDataModel(
    var memes: ArrayList<MemeModel>? = ArrayList()
)

@Entity
data class MemeModel(
    @PrimaryKey
    var id: Int? = 0,
    var url: String? = "",
    var name: String? = ""
)

