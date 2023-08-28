package com.philexliveprojects.spillatte.data

import com.google.gson.annotations.SerializedName

data class UnsplashPhoto(
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("urls") val urls: UnsplashUrls,
    @SerializedName("alt_description")val altDescription: String?,
    @SerializedName("user") val user: UnsplashUser
)
