package com.example.jetpackandroid.utils

fun verifyFields(title: String, url: String, thumbUrl: String, albumId: String): String {
    return when {
        title.isEmpty() -> {
            "Please enter title's name"
        }
        url.isEmpty() -> {
            "Please enter url's"
        }
        thumbUrl.isEmpty() -> {
            "Please enter thumb's url"
        }
        albumId.isEmpty() -> {
            "Please enter album's id"
        }
        else -> ""
    }
}