package com.damikkg.test65appsfragments.domain.repo

import com.damikkg.test65appsfragments.domain.contracts.ILocalSource
import com.damikkg.test65appsfragments.domain.contracts.IRepository
import com.damikkg.test65appsfragments.domain.models.Person
import com.damikkg.test65appsfragments.domain.models.Speciality
import javax.inject.Inject

class RepoImp @Inject constructor(
    private val localSource: ILocalSource,
): IRepository {
    override suspend fun getSpecialityList(): List<Speciality> {
        return localSource.getSpecialityList()
    }

    override suspend fun getPersonsListBySpeciality(speciality: Speciality): List<Person> {
        return localSource.getPersonListBySpeciality(speciality)
    }

    override suspend fun getPersonById(personId:Long): Person {
        return localSource.getPersonById(personId)
    }
}