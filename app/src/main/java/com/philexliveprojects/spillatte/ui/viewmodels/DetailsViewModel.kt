package com.philexliveprojects.spillatte.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.philexliveprojects.spillatte.data.CoffeeDrink
import com.philexliveprojects.spillatte.data.CoffeeDrinkRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    coffeeDrinkRepository: CoffeeDrinkRepository
) : ViewModel() {
    val uiState = coffeeDrinkRepository.get(savedStateHandle["name"] ?: "").stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = CoffeeDrink(name = "", description = "")
    )

    fun getUri() = ""

    fun hasValidUnsplashKey() = false

}
