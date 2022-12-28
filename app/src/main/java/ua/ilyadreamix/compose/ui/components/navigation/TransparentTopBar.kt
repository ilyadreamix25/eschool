package ua.ilyadreamix.compose.ui.components.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

/**
 * Transparent top app bar
 * @param navController Navigation host controller
 * @param defaultTextResource Default title text resource,
 *                            needed when current destination route is null
 * @param screens Navigation screens
 * @param mainScreen Main navigation screen
 * @author IlyaDreamix
 */
@Composable
fun TransparentTopBar(
    navController: NavController,
    @StringRes defaultTextResource: Int,
    screens: List<NavScreen>,
    mainScreen: NavScreen,
    actions: @Composable RowScope.() -> Unit
) {
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    val currentScreenText =
        if (currentDestination?.route == null)
            stringResource(defaultTextResource)
        else
            stringResource(
                screens.single {
                    it.route == currentDestination.route
                }
                    .titleResId
            )

    TopAppBar(
        title = {
            Text(
                text = currentScreenText,
                style = LocalTextStyle.current.merge(
                    TextStyle(
                        lineHeight = 2.5.em,
                        platformStyle = @Suppress("DEPRECATION") PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.None
                        )
                    )
                ),
                fontWeight = FontWeight.Bold
            )
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.systemBarsPadding(),
        actions = { actions() },
        navigationIcon =
            if (currentDestination?.route != mainScreen.route) {
                {
                    IconButton(
                        onClick = {
                            navController.navigate(mainScreen.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIos,
                            contentDescription = null
                        )
                    }
                }
            } else null
    )
}