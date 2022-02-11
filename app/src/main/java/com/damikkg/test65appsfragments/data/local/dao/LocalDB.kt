package com.damikkg.test65appsfragments.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.damikkg.test65appsfragments.data.local.dao.struct.PersonEntity
import com.damikkg.test65appsfragments.data.local.dao.struct.PersonSpecialityLink
import com.damikkg.test65appsfragments.data.local.dao.struct.SpecialityEntity

@Database(entities = [
    PersonEntity::class,
    PersonSpecialityLink::class,
    SpecialityEntity::class
], version = 1)
abstract class LocalDB: RoomDatabase() {
    abstract fun dao(): LocalDAO
}