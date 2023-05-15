package com.cpe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cpe.ui.screens.MainScaffold
import com.cpe.ui.screens.auth_screen.AuthScreen

//TODO: check if user is already signed in
@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.AuthScreen.route,
        builder = {
            composable(Screen.AuthScreen.route) {
                AuthScreen(navController = navController)
            }

            composable(Screen.MainScaffold.route) {
                MainScaffold(navController = navController)
            }
        }
    )
}