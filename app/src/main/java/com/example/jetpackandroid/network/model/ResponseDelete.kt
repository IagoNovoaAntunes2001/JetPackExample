package com.example.jetpackandroid.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseDelete (
    var id: String = ""
) : Parcelable