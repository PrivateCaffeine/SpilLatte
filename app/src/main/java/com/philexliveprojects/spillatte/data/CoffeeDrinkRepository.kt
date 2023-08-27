package com.philexliveprojects.spillatte.data

import kotlinx.coroutines.flow.Flow

interface CoffeeDrinkRepository {
    fun getAll(): Flow<List<String>>
    fun get(name: String): Flow<CoffeeDrink>
}