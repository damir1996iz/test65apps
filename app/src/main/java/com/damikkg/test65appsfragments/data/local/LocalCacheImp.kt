package com.damikkg.test65appsfragments.data.local

import com.damikkg.test65appsfragments.data.local.dao.LocalDAO
import com.damikkg.test65appsfragments.data.local.dao.struct.PersonEntity
import com.damikkg.test65appsfragments.data.local.dao.struct.PersonSpecialityLink
import com.damikkg.test65appsfragments.data.local.dao.struct.SpecialityEntity
import com.damikkg.test65appsfragments.domain.contracts.ILocalCache
import com.damikkg.test65appsfragments.domain.models.Person
import com.damikkg.test65appsfragments.domain.models.Speciality
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class LocalCacheImp @Inject constructor(
    private val localDAO: LocalDAO
): ILocalCache {
    override suspend fun insertPerson(person: Person) {
        val birthDateString = if(person.birthDate != null)
        {
            SimpleDateFormat("dd-mm-yyyy", Locale.US).format(person.birthDate)
        } else
        {
            ""
        }


        val pId = localDAO.insertPerson(
            PersonEntity(
                person.firstName,
                person.secondName,
                birthDateString,
                person.avatarUrl,
                null)
        )
        if(pId > 0)
        {
            person.speciality.forEach { value ->
                localDAO.insertPersonToSpecialityLink(
                    PersonSpecialityLink(
                        pId, value.id
                    )
                )
            }
        }
    }

    override suspend fun insertSpeciality(speciality: Speciality) {
        localDAO.insertSpeciality(
            SpecialityEntity(
            speciality.id,
            speciality.name
        )
        )
    }

}