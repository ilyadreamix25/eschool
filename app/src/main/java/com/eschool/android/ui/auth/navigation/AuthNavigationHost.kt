package com.eschool.android.ui.auth.navigation

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eschool.android.ui.auth.signup.SignUpNextScreen
import com.eschool.android.ui.auth.signup.SignUpScreen

@Composable
fun BoxScope.AuthNavigationHost(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = signUpScreen,
        modifier = Modifier.align(Alignment.Center)
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