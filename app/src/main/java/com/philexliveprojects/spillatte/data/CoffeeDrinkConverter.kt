package com.philexliveprojects.spillatte.data

import android.net.Uri
import androidx.core.net.toUri
import androidx.room.TypeConverter

class CoffeeDrinkConverter {
    @TypeConverter
    fun uriToString(value: Uri) = value.toString()

    @TypeConverter
    fun stringToUri(value: String) = value.toUri()
}