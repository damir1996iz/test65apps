package com.damikkg.test65appsfragments.data.remote

import com.damikkg.test65appsfragments.data.remote.webservice.WebService
import com.damikkg.test65appsfragments.domain.contracts.INetworkSource
import com.damikkg.test65appsfragments.domain.models.Person
import javax.inject.Inject

class RemoteSourceImp @Inject constructor(private val webService: WebService): INetworkSource {
    override suspend fun getEmployersList(): List<Person> {
        return webService.getPersonsList().personsList.map {
            Person.fromPersonResponse(it)
        }
    }
}