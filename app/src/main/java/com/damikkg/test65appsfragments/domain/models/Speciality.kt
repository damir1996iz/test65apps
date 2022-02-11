package com.damikkg.test65appsfragments.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Speciality (
    val id:Long,
    val name:String
) : Parcelable