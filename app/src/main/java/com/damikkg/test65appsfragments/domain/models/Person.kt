package com.damikkg.test65appsfragments.domain.models

import android.os.Parcelable
import com.damikkg.test65appsfragments.data.local.dao.struct.PersonEntity
import com.damikkg.test65appsfragments.data.remote.webservice.responses.PersonResponse
import kotlinx.parcelize.Parcelize
import org.joda.time.Period
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.getInstance

@Parcelize
data class Person(
    val id:Long,
    val firstName: String,
    val secondName: String,
    val birthDate: Date?,
    val avatarUrl: String,
    val speciality: List<Speciality>
) : Parcelable
{
    fun getAge():Int
    {
        return if(birthDate != null) {
            val now = getInstance().time
            Period(birthDate.time, now.time).years
        } else {
            -1
        }
    }

    companion object
    {
        fun fromPersonResponse(personResponse: PersonResponse): Person
        {
            val date = if(personResponse.birthDate != null && personResponse.birthDate.isNotEmpty())
            {
                if(Regex("..-..-....").matches(personResponse.birthDate))
                {
                    SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(personResponse.birthDate)
                }
                else
                {
                    SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(personResponse.birthDate)
                }

            }
            else
            {
                null
            }
            return Person(0,
                personResponse.firstName,
                personResponse.secondName,
                date,
                personResponse.avatarUrl?:"",
                personResponse.speciality.map { sr->
                    Speciality(sr.id, sr.name)
                }
            )
        }

        fun fromPersonEntity(personEntity: PersonEntity, specialityList: List<Speciality>): Person
        {
            val date = if(personEntity.birthDate.isNotEmpty())
            {
                SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(personEntity.birthDate)
            }
            else
            {
                null
            }

            return Person(personEntity.id ?: 0,
                    personEntity.firstName,
                    personEntity.secondName,
                    date,
                    personEntity.avatarUrl,
                    specialityList)
        }
    }
}