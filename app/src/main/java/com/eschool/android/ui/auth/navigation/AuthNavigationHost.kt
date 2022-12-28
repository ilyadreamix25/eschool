package com.eschool.android.ui.auth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eschool.android.ui.auth.signup.SignUpNextScreen
import com.eschool.android.ui.auth.signup.SignUpScreen

@Composable
fun AuthNavigationHost(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = signUpScreen
    ) {
        composable(signUpScreen) {
            SignUpScreen(navController)
        }
        composable(signInScreen) {
            // ...
        }
        composable(signUpNextScreen) {
            SignUpNextScreen(navController)
        }
    }
}