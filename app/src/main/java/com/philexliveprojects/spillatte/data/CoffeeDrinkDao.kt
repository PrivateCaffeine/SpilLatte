package com.philexliveprojects.spillatte.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.philexliveprojects.spillatte.utils.COFFEE_DRINK_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface CoffeeDrinkDao {
    @Query("SELECT * FROM $COFFEE_DRINK_TABLE;")
    fun getAll(): Flow<List<CoffeeDrink>>

    @Query(
        "SELECT * FROM $COFFEE_DRINK_TABLE " +
                "WHERE name = :name;"
    )
    fun get(name: String): Flow<CoffeeDrink>

    @Insert
    suspend fun insert(coffeeDrink: CoffeeDrink)

    @Upsert
    suspend fun upsertAll(coffeeDrinks: List<CoffeeDrink>)

    @Delete
    suspend fun delete(coffeeDrink: CoffeeDrink)
}