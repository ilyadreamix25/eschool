package com.eschool.android.ui.main.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoStories
import androidx.compose.material.icons.rounded.CalendarViewMonth
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Group
import ua.ilyadreamix.compose.ui.components.navigation.NavScreen
import com.eschool.android.R

val searchScreen = NavScreen(
    "search",
    Icons.Rounded.Search,
    R.string.search
)
val timetableScreen = NavScreen(
    "timetable",
    Icons.Rounded.CalendarViewMonth,
    R.string.timetable
)
val homeScreen = NavScreen(
    "home",
    Icons.Rounded.Home,
    R.string.app_name
)
val diaryScreen = NavScreen(
    "diary",
    Icons.Rounded.AutoStories,
    R.string.diary
)
val communitiesScreen = NavScreen(
    "communities",
    Icons.Rounded.Group,
    R.string.communities
)

val screens = listOf(
    searchScreen,
    timetableScreen,
    homeScreen,
    diaryScreen,
    communitiesScreen
)