package com.damikkg.test65appsfragments.data.local.dao.struct

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "persons",
    indices = [
        Index(value = ["firstName", "secondName", "birthDate"], unique = true)
    ]
)
data class PersonEntity(
    val firstName:String,
    val secondName:String,
    val birthDate:String,
    val avatarUrl:String,
    @PrimaryKey(autoGenerate = true)
    val id:Long?
)
