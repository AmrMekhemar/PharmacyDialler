package com.team.myapplication.register.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Coordinates  (
	val lat : Double,
	val lon : Double
) : Parcelable
