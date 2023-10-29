package com.philexliveprojects.spillatte.ui.compose.details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text

fun LazyStaggeredGridScope.detailsError(onRefresh: () -> Unit) = item {
    Row {
        Text(text = "Error")
        Button(onClick = onRefresh) {
            Text(text = "Refresh")
        }
    }
}