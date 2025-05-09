package com.na2te.second.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.na2te.second.Greeting
import kotlinx.serialization.Serializable

@Serializable data object SecondRoute
@Serializable data object SecondBaseRoute

fun NavGraphBuilder.secondSection(onNavigationClick: () -> Unit) {
    navigation<SecondBaseRoute>(startDestination = SecondRoute) {
        composable<SecondRoute> {
            Greeting("Second", onClick = onNavigationClick)
        }
    }
}

fun NavController.navigateToSecond() {
    navigate(route = SecondBaseRoute)
}