package com.philexliveprojects.spillatte.data

import kotlinx.coroutines.flow.Flow

interface CoffeeDrinkRepository {
    fun getAll(): Flow<List<CoffeeDrink>>
    fun get(name: String): Flow<CoffeeDrink>
    suspend fun insert(coffeeDrink: CoffeeDrink)
    suspend fun delete(coffeeDrink: CoffeeDrink)
}