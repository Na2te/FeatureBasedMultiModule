package com.na2te.featurebasedmultimodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.na2te.featurebasedmultimodule.ui.theme.FeatureBasedMultiModuleTheme
import com.na2te.second.navigation.navigateToSecond
import com.na2te.second.navigation.secondSection
import com.na2te.start.navigation.StartBaseRoute
import com.na2te.start.navigation.navigateToStart
import com.na2te.start.navigation.startSection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val snackBarHostState = remember { SnackbarHostState() }
            FeatureBasedMultiModuleTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(
                            snackBarHostState
                        )

                    }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = StartBaseRoute,
                        ) {
                            startSection(
                                navController::navigateToSecond,
                                onShowSnackBar = { message, action ->
                                    snackBarHostState.showSnackbar(
                                        message = message,
                                        actionLabel = action,
                                        duration = SnackbarDuration.Short
                                    ) == ActionPerformed
                                }
                            )
                            secondSection(navController::navigateToStart)
                        }
                    }
                }
            }
        }
    }
}