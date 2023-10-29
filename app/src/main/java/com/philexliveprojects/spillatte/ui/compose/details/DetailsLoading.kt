package com.philexliveprojects.spillatte.ui.compose.details

import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.material3.Text

fun LazyStaggeredGridScope.detailsLoading() = item {
    Text(text = "Loading")
}
