package com.philexliveprojects.spillatte.data

import kotlinx.coroutines.flow.Flow

class OfflineCoffeeDrinkRepository(
    private val coffeeDrinkDao: CoffeeDrinkDao
) : CoffeeDrinkRepository {
    override fun getAll(): Flow<List<CoffeeDrinkRef>> = coffeeDrinkDao.getAll()

    override fun get(id: Int): Flow<CoffeeDrinkDetails> = coffeeDrinkDao.get(id)
}