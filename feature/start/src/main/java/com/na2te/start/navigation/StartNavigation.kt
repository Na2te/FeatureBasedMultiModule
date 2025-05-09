package com.na2te.start.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.na2te.start.Greeting
import kotlinx.serialization.Serializable

@Serializable data object StartRoute
@Serializable data object StartBaseRoute

fun NavGraphBuilder.startSection(onNavigationClick: () -> Unit) {
    navigation<StartBaseRoute>(startDestination = StartRoute) {
        composable<StartRoute> {
            Greeting("Na2te", onClick = onNavigationClick)


        }
    }
}

fun NavController.navigateToStart() {
    navigate(route = StartBaseRoute)
}