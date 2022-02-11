package com.damikkg.test65appsfragments.domain.contracts

import com.damikkg.test65appsfragments.domain.models.Person
import com.damikkg.test65appsfragments.domain.models.Speciality

interface ILocalSource {
    suspend fun getSpecialityList():List<Speciality>
    suspend fun getPersonListBySpeciality(speciality: Speciality):List<Person>
    suspend fun getPersonById(personId: Long): Person
}