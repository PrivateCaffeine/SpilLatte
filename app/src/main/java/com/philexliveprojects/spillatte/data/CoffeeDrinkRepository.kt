package com.philexliveprojects.spillatte.data

import kotlinx.coroutines.flow.Flow

interface CoffeeDrinkRepository {
    fun getAll(): Flow<List<CoffeeDrinkRef>>
    fun get(id: Int): Flow<CoffeeDrinkDetails>
}