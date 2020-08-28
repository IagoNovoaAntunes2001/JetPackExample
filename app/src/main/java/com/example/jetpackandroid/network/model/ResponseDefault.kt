package com.example.jetpackandroid.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseDefault(
    var id: String = ""
) : Parcelable