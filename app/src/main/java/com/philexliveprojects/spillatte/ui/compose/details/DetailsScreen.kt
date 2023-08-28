package com.philexliveprojects.spillatte.ui.compose.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.philexliveprojects.spillatte.R
import com.philexliveprojects.spillatte.data.Coffee
import com.philexliveprojects.spillatte.data.UnsplashPhoto
import com.philexliveprojects.spillatte.data.UnsplashUrls
import com.philexliveprojects.spillatte.data.UnsplashUser
import com.philexliveprojects.spillatte.ui.AppViewModelProvider
import com.philexliveprojects.spillatte.ui.theme.PrimaryContentPadding
import com.philexliveprojects.spillatte.ui.theme.SpilLatteTheme
import com.philexliveprojects.spillatte.ui.viewmodels.DetailsViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun DetailsScreen(
    onBack: () -> Unit = {},
    viewModel: DetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coffee by viewModel.coffee.collectAsState()
    DetailsScreen(
        coffee,
        viewModel.photos,
        viewModel.hasValidUnsplashKey(),
        onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsScreen(
    coffee: Coffee,
    unsplashPhotos: Flow<PagingData<UnsplashPhoto>>,
    hasValidUnsplashKey: Boolean = false,
    onBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(coffee.name) },
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
        val photos = unsplashPhotos.collectAsLazyPagingItems()

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PrimaryContentPadding,
            modifier = Modifier.padding(paddingValues)
        ) {
            /*Coffee description*/
            item(span = StaggeredGridItemSpan.FullLine) {
                Column {
                    Text(coffee.description)
                    Spacer(Modifier.height(12.dp))
                }
            }
            if (hasValidUnsplashKey) {
                /*Unsplash photos list*/
                items(
                    count = photos.itemCount,
                    key = { index -> "${photos[index]?.id ?: ""}${index}" }
                ) { index ->
                    val item = photos[index] ?: return@items
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.urls.small)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surface)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview(
    @PreviewParameter(DetailsPreviewProvider::class) param: DetailsParameter
) {
    SpilLatteTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            DetailsScreen(param.coffee, param.photos, true)
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreviewDark(
    @PreviewParameter(DetailsPreviewProvider::class) param: DetailsParameter
) {
    SpilLatteTheme(
        darkTheme = true
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            DetailsScreen(param.coffee, param.photos, true)
        }
    }
}

private class DetailsPreviewProvider(
    override val values: Sequence<DetailsParameter> = sequenceOf(
        DetailsParameter(
            Coffee("Coffee", "Coffee Description"),
            flow {
                PagingData.from(
                    (1..24).map {
                        UnsplashPhoto(
                            it.toString(),
                            UnsplashUrls("null"),
                            null,
                            UnsplashUser("User $it")
                        )
                    }
                )
            }

        )
    )
) : PreviewParameterProvider<DetailsParameter>

class DetailsParameter(val coffee: Coffee, val photos: Flow<PagingData<UnsplashPhoto>>)