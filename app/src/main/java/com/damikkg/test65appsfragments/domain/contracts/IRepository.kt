package com.damikkg.test65appsfragments.domain.contracts

import com.damikkg.test65appsfragments.domain.models.Person
import com.damikkg.test65appsfragments.domain.models.Speciality

interface IRepository {
    suspend fun getSpecialityList():List<Speciality>
    suspend fun getPersonsListBySpeciality(speciality: Speciality):List<Person>
    suspend fun getPersonById(personId: Long): Person
}