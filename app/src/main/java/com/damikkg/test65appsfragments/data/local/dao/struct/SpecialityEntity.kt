package com.damikkg.test65appsfragments.data.local.dao.struct

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "specialities"
)
data class SpecialityEntity(
    @PrimaryKey
    val id:Long,
    val name:String
)
