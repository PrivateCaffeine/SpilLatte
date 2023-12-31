package com.philexliveprojects.spillatte.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.philexliveprojects.spillatte.utils.COFFEE_DRINK_TABLE

@Entity(tableName = COFFEE_DRINK_TABLE)
data class Coffee(
    @PrimaryKey val name: String,
    val description: String
)