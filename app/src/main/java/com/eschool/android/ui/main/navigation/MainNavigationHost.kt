package com.eschool.android.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavigationHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController,
        startDestination = homeScreen.route,
        modifier = modifier
    ) {
        composable(searchScreen.route) {
            // ...
        }
        composable(timetableScreen.route) {
            // ...
        }
        composable(homeScreen.route) {
            // ...
        }
        composable(diaryScreen.route) {
            // ...
        }
        composable(communitiesScreen.route) {
            // ...
        }
    }
}