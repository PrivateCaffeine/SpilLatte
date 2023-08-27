package com.philexliveprojects.spillatte.data

import kotlinx.coroutines.flow.Flow

class OfflineCoffeeDrinkRepository(
    private val coffeeDrinkDao: CoffeeDrinkDao
) : CoffeeDrinkRepository {
    override fun getAll(): Flow<List<String>> = coffeeDrinkDao.getAll()

    override fun get(name: String): Flow<CoffeeDrink> = coffeeDrinkDao.get(name)
}