package com.damikkg.test65appsfragments.domain.contracts

import com.damikkg.test65appsfragments.domain.models.Person
import com.damikkg.test65appsfragments.domain.models.Speciality

interface ILocalCache {
    suspend fun insertPerson(person: Person)
    suspend fun insertSpeciality(speciality: Speciality)
}