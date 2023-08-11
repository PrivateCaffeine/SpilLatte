package com.philexliveprojects.spillatte.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.philexliveprojects.spillatte.ui.compose.home.HomeScreen

object SpilLatteDestinations {
    const val HOME = "home"
    const val DETAILS = "details/{coffeeDrink}"
    const val EDIT = "editDetails{coffeeDrink}"
}

@Composable
fun SpilLatteNavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = SpilLatteDestinations.HOME,
        modifier = modifier
    ) {
        composable(SpilLatteDestinations.HOME) {
            HomeScreen(navigateToDetails = {})
        }

        composable(
            route = SpilLatteDestinations.DETAILS,
            arguments = listOf(navArgument("coffeeDrink") { type = NavType.StringType })
        ) { navBackStackEntry ->
            /*TODO*/
        }

        composable(
            route = SpilLatteDestinations.EDIT,
            arguments = listOf(navArgument("coffeeDrink") { type = NavType.StringType })
        ) { navBackStackEntry ->
            /*TODO*/
        }
    }
}