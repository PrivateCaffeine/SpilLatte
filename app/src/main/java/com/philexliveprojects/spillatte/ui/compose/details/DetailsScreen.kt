package com.philexliveprojects.spillatte.ui.compose.details

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.philexliveprojects.spillatte.R
import com.philexliveprojects.spillatte.data.CoffeeDrink
import com.philexliveprojects.spillatte.ui.AppViewModelProvider
import com.philexliveprojects.spillatte.ui.theme.PrimaryContentPadding
import com.philexliveprojects.spillatte.ui.theme.SpilLatteTheme
import com.philexliveprojects.spillatte.ui.viewmodels.DetailsViewModel

@Composable
fun DetailsScreen(
    onBack: () -> Unit = {},
    viewModel: DetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    DetailsScreen(uiState, viewModel.hasValidUnsplashKey(), onBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsScreen(
    coffeeDrink: CoffeeDrink,
    hasValidUnsplashKey: Boolean,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(coffeeDrink.name) },
                navigationIcon = {
                    OutlinedIconButton(
                        onClick = onBack,

                        ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back_to_home)
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(PrimaryContentPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CoffeeDescription(text = coffeeDrink.description)
            if (hasValidUnsplashKey) {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {

                }
            }
        }
    }
}

@Composable
fun CoffeeImage(
    uri: String,
    context: Context,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(uri)
            .error(R.drawable.ic_launcher_foreground)
            .build(),
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
fun CoffeeDescription(
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.Start)
                .padding(16.dp)
        )
    }
}


@Preview
@Composable
fun DetailsScreenPreview(@PreviewParameter(CoffeeDetailsPreviewProvider::class) param: CoffeeDrink) {
    SpilLatteTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            DetailsScreen(param, false, {})
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreviewWithImage(@PreviewParameter(CoffeeDetailsPreviewProvider::class) param: CoffeeDrink) {
    SpilLatteTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            DetailsScreen(param, true, {})
        }
    }
}

class CoffeeDetailsPreviewProvider(
    override val values: Sequence<CoffeeDrink> = sequenceOf(
        CoffeeDrink("Coffee", "Coffee Description")
    )
) : PreviewParameterProvider<CoffeeDrink>