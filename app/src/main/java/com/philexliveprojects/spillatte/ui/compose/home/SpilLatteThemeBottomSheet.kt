package com.philexliveprojects.spillatte.ui.compose.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.philexliveprojects.spillatte.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpilLatteThemeBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    onOption: (String) -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState()
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState
    ) {
        SpilLatteThemeBottomSheetContent(onOption)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpilLatteThemeBottomSheetContent(
    onOption: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(12.dp)
    ) {
        Text(
            text = stringResource(R.string.theme),
            style = MaterialTheme.typography.labelMedium
        )

        val options = listOf("default", "light", "dark")
        var selected by remember { mutableStateOf(options[0]) }
        Row(
            modifier = Modifier
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            options.forEach { option ->
                FilterChip(
                    selected = option == selected,
                    onClick = {
                        onOption(option)
                        selected = option
                    },
                    label = { Text(text = option) })
            }
        }
    }
}

@Preview
@Composable
fun SpilLatteThemeBottomSheet() {
    SpilLatteThemeBottomSheetContent({})
}