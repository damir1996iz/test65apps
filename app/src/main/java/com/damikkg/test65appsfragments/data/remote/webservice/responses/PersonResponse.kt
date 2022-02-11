package com.damikkg.test65appsfragments.data.remote.webservice.responses

import com.google.gson.annotations.SerializedName

data class PersonResponse (
    @SerializedName("f_name")
    val firstName: String,
    @SerializedName("l_name")
    val secondName: String,
    @SerializedName("birthday")
    val birthDate: String?,
    @SerializedName("avatr_url")
    val avatarUrl: String?,
    @SerializedName("specialty")
    val speciality: List<SpecialityResponse>
)