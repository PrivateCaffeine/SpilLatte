package com.philexliveprojects.spillatte.data

import android.content.Context
import com.philexliveprojects.spillatte.api.UnsplashService

interface AppContainer {
    val coffeeRepository: CoffeeRepository
    val unsplashRepository: UnsplashRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val coffeeRepository: CoffeeRepository by lazy {
        CoffeeRepository(AppDatabase.getInstance(context).coffeeDrinkDao())
    }
    override val unsplashRepository: UnsplashRepository by lazy {
        UnsplashRepository(UnsplashService.retrofit)
    }
}