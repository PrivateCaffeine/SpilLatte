package com.philexliveprojects.spillatte.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.philexliveprojects.spillatte.utils.COFFEE_DRINK_TABLE

@Entity(tableName = COFFEE_DRINK_TABLE)
data class CoffeeDrink(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    val uri: String
)
