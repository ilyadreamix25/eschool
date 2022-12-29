package com.eschool.android.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.eschool.android.ui.auth.navigation.AuthNavigationHost
import com.eschool.android.ui.theme.BackgroundUtility

@Composable
fun AuthScreen() {
    val navController = rememberNavController()

    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.background(BackgroundUtility.getGradient())) {
            AuthNavigationHost(navController)
        }
    }
}