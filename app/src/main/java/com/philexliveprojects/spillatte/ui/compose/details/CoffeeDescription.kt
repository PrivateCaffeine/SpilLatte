package com.philexliveprojects.spillatte.ui.compose.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CoffeeDescription(text: String, modifier: Modifier = Modifier) {
    OutlinedCard(modifier.fillMaxWidth()) {
        Text(text, Modifier.padding(16.dp))
    }
}