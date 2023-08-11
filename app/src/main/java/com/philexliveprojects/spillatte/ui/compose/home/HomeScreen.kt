package com.philexliveprojects.spillatte.ui.compose.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.philexliveprojects.spillatte.R
import com.philexliveprojects.spillatte.data.CoffeeDrink
import com.philexliveprojects.spillatte.ui.AppViewModelProvier
import com.philexliveprojects.spillatte.ui.viewmodels.HomeViewModel


@Composable
fun HomeScreen(
    navigateToDetails: (String) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvier.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    HomeScreenContent(uiState.list, navigateToDetails = { navigateToDetails(it) })
}


@Composable
fun HomeScreenContent(
    list: List<CoffeeDrink>,
    modifier: Modifier = Modifier,
    navigateToDetails: (String) -> Unit = {}
) {
    Column(modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(start = 12.dp, top = 8.dp, end = 12.dp, bottom = 24.dp)
        ) {
            items(items = list) { coffeeDrink ->
                CoffeeBox(
                    title = coffeeDrink.name,
                    onClick = { navigateToDetails(coffeeDrink.name) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeBox(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(onClick = onClick, modifier = modifier.size(120.dp)) {
        Column(Modifier.fillMaxSize()) {
            Image(
                painterResource(R.drawable.ic_launcher_foreground),
                null,
                Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
            Text(title, Modifier.padding(12.dp), style = MaterialTheme.typography.bodyMedium)
        }

    }
}


@Preview
@Composable
fun CoffeeBoxPreview() {
    CoffeeBox(
        title = "Title",
        onClick = {}
    )
}