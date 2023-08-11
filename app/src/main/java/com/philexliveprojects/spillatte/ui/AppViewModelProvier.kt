package com.philexliveprojects.spillatte.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.philexliveprojects.spillatte.SpilLatteApplication
import com.philexliveprojects.spillatte.ui.viewmodels.HomeViewModel

object AppViewModelProvier {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(spilLatteApplication().container.coffeeDrinkRepository)
        }
    }
}

fun CreationExtras.spilLatteApplication(): SpilLatteApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SpilLatteApplication)