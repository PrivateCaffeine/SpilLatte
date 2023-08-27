package com.philexliveprojects.spillatte.ui.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SpilLatteApp() {
    SpilLatteAppContent()
}

@Composable
fun SpilLatteAppContent() {
    val navHostController = rememberNavController()
    SpilLatteNavGraph(navHostController = navHostController)
}
