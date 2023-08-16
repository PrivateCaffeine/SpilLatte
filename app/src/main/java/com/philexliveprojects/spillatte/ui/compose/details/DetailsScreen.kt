package com.philexliveprojects.spillatte.ui.compose.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.philexliveprojects.spillatte.data.CoffeeDrinkDetails
import com.philexliveprojects.spillatte.ui.AppViewModelProvider
import com.philexliveprojects.spillatte.ui.theme.SpilLatteTheme
import com.philexliveprojects.spillatte.ui.viewmodels.DetailsViewModel

@Composable
fun DetailsScreen(
    onBack: () -> Unit = {},
    viewModel: DetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    DetailsScreenContent(uiState, onBack)
}

@Composable
fun DetailsScreenContent(
    coffeeDrink: CoffeeDrinkDetails,
    onBack: () -> Unit
) {
    /*TODO*/
}

@Preview
@Composable
fun DetailsScreenPreview() {
    SpilLatteTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            /*TODO*/
        }
    }
}