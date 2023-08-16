package com.philexliveprojects.spillatte.data

import androidx.room.Dao
import androidx.room.Query
import com.philexliveprojects.spillatte.utils.COFFEE_DRINK_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface CoffeeDrinkDao {
    @Query(
        "SELECT id, name, uri " +
                "FROM $COFFEE_DRINK_TABLE " +
                "ORDER BY name ASC;"
    )
    fun getAll(): Flow<List<CoffeeDrinkRef>>

    @Query(
        "SELECT name, description, uri " +
                "FROM $COFFEE_DRINK_TABLE " +
                "WHERE id = :id;"
    )
    fun get(id: Int): Flow<CoffeeDrinkDetails>
}