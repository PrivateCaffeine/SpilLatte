package com.philexliveprojects.spillatte.ui.compose.utilities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.philexliveprojects.spillatte.ui.theme.PrimaryContentPadding

@Composable
fun SpilLatteList(
    modifier: Modifier = Modifier,
    state: LazyStaggeredGridState = rememberLazyStaggeredGridState(),
    content: LazyStaggeredGridScope.() -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier,
        state = state,
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PrimaryContentPadding,
        content = content
    )
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun SpilLatteListPreview(@PreviewParameter(SpilLatteListProvider::class) param: List<SampleEntity>) {
    SpilLatteList {
        items(param) {
            Card(
                modifier = Modifier
                    .height(it.height.dp)
            ) { }
        }
    }
}

private class SpilLatteListProvider(
    override val values: Sequence<List<SampleEntity>> = sequenceOf(
        (1..12).map {
            SampleEntity(
                height = (150..400).random()
            )
        }
    )
) : PreviewParameterProvider<List<SampleEntity>>

data class SampleEntity(val height: Int)