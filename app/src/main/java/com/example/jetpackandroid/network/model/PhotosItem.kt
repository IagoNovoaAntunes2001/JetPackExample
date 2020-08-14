package com.example.jetpackandroid.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotosItem(
    @Json(name = "id")
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
    val albumId: Int
) : Parcelable