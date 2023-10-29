package com.philexliveprojects.spillatte.ui.compose.details

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.philexliveprojects.spillatte.data.UnsplashPhoto

fun LazyStaggeredGridScope.detailsSuccess(
    count: Int,
    key: ((Int) -> Any)?,
    items: LazyPagingItems<UnsplashPhoto>
) = items(count, key) { index ->
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(items[index]?.urls?.small)
            .build(),
        contentDescription = null,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .clip(MaterialTheme.shapes.medium),
        contentScale = ContentScale.Crop
    )
}