package com.philexliveprojects.spillatte.data

import kotlinx.coroutines.flow.Flow

class OfflineCoffeeDrinkRepository(
    private val coffeeDrinkDao: CoffeeDrinkDao
) : CoffeeDrinkRepository {
    override fun getAll(): Flow<List<CoffeeDrink>> = coffeeDrinkDao.getAll()

    override fun get(name: String): Flow<CoffeeDrink> = coffeeDrinkDao.get(name)

    override suspend fun insert(coffeeDrink: CoffeeDrink) = coffeeDrinkDao.insert(coffeeDrink)

    override suspend fun delete(coffeeDrink: CoffeeDrink) = coffeeDrinkDao.delete(coffeeDrink)
}