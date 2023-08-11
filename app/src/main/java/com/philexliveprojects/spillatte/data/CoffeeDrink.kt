package com.philexliveprojects.spillatte.data

import androidx.room.Entity
import com.philexliveprojects.spillatte.utils.COFFEE_DRINK_TABLE

@Entity(tableName = COFFEE_DRINK_TABLE, primaryKeys = ["name", "description"])
data class CoffeeDrink(
    val name: String,
    val description: String
)
