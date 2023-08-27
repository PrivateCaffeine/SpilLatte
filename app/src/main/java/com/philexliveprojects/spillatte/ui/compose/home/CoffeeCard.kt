package com.philexliveprojects.spillatte.ui.compose.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.philexliveprojects.spillatte.R

private val CoffeeCardMinWidth = 160.dp
private val CoffeeCardMinHeight = 120.dp
private val CoffeeCardMaxWidth = 200.dp
private val CoffeeCardMaxHeight = 160.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeCard(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier.sizeIn(
            minWidth = CoffeeCardMinWidth,
            minHeight = CoffeeCardMinHeight,
            maxWidth = CoffeeCardMaxWidth,
            maxHeight = CoffeeCardMaxHeight
        )
    ) {
        Column(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.spillatte),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
            )
            Text(
                text = title,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(48.dp)
                    .wrapContentHeight(),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview
@Composable
fun CoffeeCardPreview() {
    CoffeeCard(
        title = "Title",
        onClick = {}
    )
}