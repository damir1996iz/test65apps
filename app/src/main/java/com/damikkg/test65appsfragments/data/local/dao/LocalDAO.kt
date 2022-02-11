package com.damikkg.test65appsfragments.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.damikkg.test65appsfragments.data.local.dao.struct.PersonEntity
import com.damikkg.test65appsfragments.data.local.dao.struct.PersonSpecialityLink
import com.damikkg.test65appsfragments.data.local.dao.struct.SpecialityEntity

@Dao
interface LocalDAO {
    @Query ("SELECT * FROM persons WHERE id = :id")
    fun getPerson(id:Long): PersonEntity

    @Query("SELECT * FROM persons WHERE id in(SELECT personId FROM personToSpecialityLinks WHERE specialityId = :sId)")
    fun getPersonsListBySpecialityId(sId:Long):List<PersonEntity>

    @Query("SELECT * FROM specialities")
    fun getAllSpecialitiesList():List<SpecialityEntity>

    @Query("SELECT * FROM specialities WHERE id in (SELECT specialityId FROM personToSpecialityLinks WHERE personId = :pId)")
    fun getPersonSpecialitiesList(pId:Long):List<SpecialityEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPerson(personEntity: PersonEntity):Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSpeciality(specialityEntity: SpecialityEntity):Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPersonToSpecialityLink(personSpecialityLink: PersonSpecialityLink):Long
}