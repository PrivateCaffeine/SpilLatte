package com.philexliveprojects.spillatte.data

import kotlinx.coroutines.flow.Flow

class CoffeeRepository(
    private val coffeeDao: CoffeeDao
) {
    fun getAll(): Flow<List<String>> = coffeeDao.getAll()

    fun get(name: String): Flow<Coffee> = coffeeDao.get(name)
}