package com.philexliveprojects.spillatte.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.philexliveprojects.spillatte.BuildConfig
import com.philexliveprojects.spillatte.data.Coffee
import com.philexliveprojects.spillatte.data.CoffeeRepository
import com.philexliveprojects.spillatte.data.UnsplashRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    coffeeRepository: CoffeeRepository,
    unsplashRepository: UnsplashRepository
) : ViewModel() {
    private val coffeeName = savedStateHandle["name"] ?: ""

    val coffee = coffeeRepository.get(coffeeName).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = Coffee(name = "", description = "")
    )

    val unsplashPhotos = unsplashRepository.getPhotos(coffeeName)
        .cachedIn(viewModelScope)


    fun hasValidUnsplashKey() = BuildConfig.UNSPLASH_ACCESS_KEY != "null"
}