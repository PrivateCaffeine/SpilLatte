package com.philexliveprojects.spillatte.ui.compose.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.philexliveprojects.spillatte.R
import com.philexliveprojects.spillatte.ui.AppViewModelProvider
import com.philexliveprojects.spillatte.ui.theme.PrimaryContentPadding
import com.philexliveprojects.spillatte.ui.theme.SpilLatteTheme
import com.philexliveprojects.spillatte.ui.viewmodels.HomeViewModel

/**
 * Stateful home screen layout
 */
@Composable
fun HomeScreen(
    onDetails: (String) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    HomeScreen(uiState, navigateToDetails = { onDetails(it) })
}

/**
 * Base home screen layout
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    coffeeDrinks: List<String>,
    modifier: Modifier = Modifier,
    navigateToDetails: (String) -> Unit = {}
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    // Outlined button for opening theme settings
                    OutlinedIconButton(onClick = { showBottomSheet = true }) {
                        Icon(
                            painter = painterResource(R.drawable.palette_24px),
                            contentDescription = stringResource(R.string.customization)
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            // List of coffee drinks
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PrimaryContentPadding
            ) {
                items(items = coffeeDrinks) { drink ->
                    CoffeeCard(
                        title = drink,
                        onClick = { navigateToDetails(drink) }
                    )
                }
            }
        }

        // Opens theme settings if shoBottomSheet is true
        if (showBottomSheet) {
            SpilLatteThemeBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                onOption = { /*TODO*/ }
            )
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview(@PreviewParameter(HomePreviewParameterProvider::class) param: List<String>) {
    SpilLatteTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            HomeScreen(param)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreviewDark(@PreviewParameter(HomePreviewParameterProvider::class) param: List<String>) {
    SpilLatteTheme(darkTheme = true) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            HomeScreen(param)
        }
    }
}


/*Home screen preview parameter provider*/
private class HomePreviewParameterProvider(
    override val values: Sequence<List<String>> = sequenceOf((1..24).map { "Coffee $it" })
) : PreviewParameterProvider<List<String>>
