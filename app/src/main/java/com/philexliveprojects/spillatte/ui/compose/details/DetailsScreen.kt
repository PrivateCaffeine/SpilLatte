package com.philexliveprojects.spillatte.ui.compose.details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.philexliveprojects.spillatte.R
import com.philexliveprojects.spillatte.data.Coffee
import com.philexliveprojects.spillatte.data.UnsplashPhoto
import com.philexliveprojects.spillatte.ui.AppViewModelProvider
import com.philexliveprojects.spillatte.ui.compose.utilities.SpilLatteList
import com.philexliveprojects.spillatte.ui.viewmodels.DetailsViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onBack: () -> Unit = {}
) {
    val coffee by viewModel.coffee.collectAsState()
    DetailsScreen(
        coffee,
        viewModel.unsplashPhotos,
        viewModel.hasValidUnsplashKey(),
        onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsScreen(
    coffee: Coffee,
    unsplashPhotos: Flow<PagingData<UnsplashPhoto>>,
    hasValidUnsplashKey: Boolean,
    onBack: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(coffee.name) },
                navigationIcon = {
                    OutlinedIconButton(onBack) {
                        Icon(Icons.Default.ArrowBack, stringResource(R.string.back_to_home))
                    }
                }
            )
        }
    ) { paddingValues ->
        if (hasValidUnsplashKey) {
            val items = unsplashPhotos.collectAsLazyPagingItems()

            SpilLatteList(Modifier.padding(paddingValues)) {
                item(span = StaggeredGridItemSpan.FullLine) { CoffeeDescription(coffee.description) }
                when (items.loadState.refresh) {
                    is LoadState.Loading -> {
                        detailsLoading()
                    }

                    is LoadState.Error -> {
                        detailsError(onRefresh = { items.retry() })
                    }

                    else -> {
                        detailsSuccess(
                            count = items.itemCount,
                            key = { it },
                            items = items
                        )
                        if (items.loadState.append is LoadState.Error) {
                            item(span = StaggeredGridItemSpan.FullLine) {
                                Row {
                                    Text("Reload")
                                    Button(onClick = { items.retry() }) {
                                        Text(text = "Retry")
                                    }
                                }
                            }
                        } else if (items.loadState.append is LoadState.Loading) {
                            item(span = StaggeredGridItemSpan.FullLine) { Text("Loading") }
                        }
                    }
                }
            }
        } else {
            CoffeeDescription(
                coffee.description,
                Modifier
                    .padding(PaddingValues(horizontal = 16.dp))
                    .padding(paddingValues)
            )
        }
    }
}