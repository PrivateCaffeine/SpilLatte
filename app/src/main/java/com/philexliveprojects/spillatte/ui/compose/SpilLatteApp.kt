package com.philexliveprojects.spillatte.ui.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.philexliveprojects.spillatte.R

@Composable
fun SpilLatteApp() {
    SpilLatteAppContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpilLatteAppContent() {
    val navHostController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(title = { /*TODO*/ })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(SpilLatteDestinations.EDIT)
                }
            ) {
                Icon(Icons.Default.Add, stringResource(R.string.add_coffee_drink))
            }
        }
    ) { paddingValues ->
        SpilLatteNavGraph(
            navHostController = navHostController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}