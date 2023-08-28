package com.philexliveprojects.spillatte.data

import com.google.gson.annotations.SerializedName

data class UnsplashSearchResponce(
    @SerializedName("results") val results: List<UnsplashPhoto>,
    @SerializedName("total_pages") val totalPages: Int
)