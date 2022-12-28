package com.eschool.android.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.eschool.android.ui.main.navigation.MainNavigationHost
import com.eschool.android.ui.main.navigation.screens as mainScreens
import com.eschool.android.ui.theme.BackgroundUtility
import com.eschool.android.R
import com.eschool.android.ui.main.navigation.homeScreen
import ua.ilyadreamix.compose.ui.components.navigation.TransparentNavBar
import ua.ilyadreamix.compose.ui.components.navigation.TransparentTopBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.background(BackgroundUtility.getGradient())) {
            Scaffold(
                topBar = {
                    TransparentTopBar(
                        navController,
                        defaultTextResource = R.string.app_name,
                        screens = mainScreens,
                        mainScreen = homeScreen
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Rounded.Menu,
                                contentDescription = null
                            )
                        }
                    }
                },
                bottomBar = {
                    TransparentNavBar(
                        mainScreens,
                        navController,
                        false
                    )
                },
                backgroundColor = Color.Transparent
            ) {
                MainNavigationHost(
                    navController,
                    Modifier.fillMaxSize().padding(it)
                )
            }
        }
    }
}
