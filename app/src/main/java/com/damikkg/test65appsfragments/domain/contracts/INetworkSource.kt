package com.damikkg.test65appsfragments.domain.contracts

import com.damikkg.test65appsfragments.domain.models.Person

interface INetworkSource {
    suspend fun getEmployersList():List<Person>
}