package com.damikkg.test65appsfragments.data.local

import com.damikkg.test65appsfragments.data.local.dao.LocalDAO
import com.damikkg.test65appsfragments.domain.contracts.ILocalSource
import com.damikkg.test65appsfragments.domain.models.Person
import com.damikkg.test65appsfragments.domain.models.Speciality
import javax.inject.Inject

class LocalSourceImp @Inject constructor(
    private val localDAO: LocalDAO
) : ILocalSource {
    override suspend fun getSpecialityList(): List<Speciality> {
        return localDAO.getAllSpecialitiesList().map {
            Speciality(it.id, it.name)
        }
    }

    override suspend fun getPersonListBySpeciality(speciality: Speciality): List<Person> {
        return localDAO.getPersonsListBySpecialityId(speciality.id).map { pe->
            Person.fromPersonEntity(pe,localDAO.getPersonSpecialitiesList(pe.id!!).map { se->
                Speciality(se.id,se.name)
            })
        }
    }

    override suspend fun getPersonById(personId: Long): Person {
        return Person.fromPersonEntity(
            localDAO.getPerson(personId),
            localDAO.getPersonSpecialitiesList(personId).map {
            Speciality(it.id,it.name)
        })
    }

}