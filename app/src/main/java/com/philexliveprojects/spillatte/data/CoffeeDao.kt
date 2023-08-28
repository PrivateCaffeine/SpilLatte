package com.philexliveprojects.spillatte.data

import androidx.room.Dao
import androidx.room.Query
import com.philexliveprojects.spillatte.utils.COFFEE_DRINK_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface CoffeeDao {
    @Query(
        "SELECT name " +
                "FROM $COFFEE_DRINK_TABLE " +
                "ORDER BY name ASC;"
    )
    fun getAll(): Flow<List<String>>

    @Query(
        "SELECT * " +
                "FROM $COFFEE_DRINK_TABLE " +
                "WHERE name = :name;"
    )
    fun get(name: String): Flow<Coffee>
}
