package com.damikkg.test65appsfragments.data.remote.webservice.responses

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("response")
    val personsList:List<PersonResponse>
)
