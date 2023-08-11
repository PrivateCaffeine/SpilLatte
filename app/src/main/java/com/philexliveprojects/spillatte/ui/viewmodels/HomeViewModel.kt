package com.philexliveprojects.spillatte.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.philexliveprojects.spillatte.data.CoffeeDrink
import com.philexliveprojects.spillatte.data.CoffeeDrinkRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(coffeeDrinkRepository: CoffeeDrinkRepository) : ViewModel() {
    val uiState = coffeeDrinkRepository.getAll()
        .map { HomeUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = HomeUiState(emptyList())
        )
}

data class HomeUiState(val list: List<CoffeeDrink>)