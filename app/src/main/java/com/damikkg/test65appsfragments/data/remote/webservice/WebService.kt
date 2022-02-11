package com.damikkg.test65appsfragments.data.remote.webservice

import com.damikkg.test65appsfragments.data.remote.webservice.responses.Response
import retrofit2.http.GET

interface WebService {
    @GET("/65gb/static/raw/master/testTask.json")
    suspend fun getPersonsList(): Response
}