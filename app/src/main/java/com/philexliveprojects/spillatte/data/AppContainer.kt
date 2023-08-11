package com.philexliveprojects.spillatte.data

import android.content.Context

interface AppContainer {
    val coffeeDrinkRepository: CoffeeDrinkRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val coffeeDrinkRepository: CoffeeDrinkRepository by lazy {
        OfflineCoffeeDrinkRepository(AppDatabase.getInstance(context).coffeeDrinkDao())
    }
}