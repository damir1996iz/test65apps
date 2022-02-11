package com.damikkg.test65appsfragments.data.local.dao.struct

import androidx.room.Entity

@Entity(
    tableName = "personToSpecialityLinks",
    primaryKeys = ["personId", "specialityId"])
data class PersonSpecialityLink(
    val personId : Long,
    val specialityId : Long
)
