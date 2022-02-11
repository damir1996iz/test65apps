package com.damikkg.test65appsfragments.data.remote.webservice.responses

import com.google.gson.annotations.SerializedName

data class SpecialityResponse (
    @SerializedName("specialty_id")
    val id:Long,
    @SerializedName("name")
    val name:String
)