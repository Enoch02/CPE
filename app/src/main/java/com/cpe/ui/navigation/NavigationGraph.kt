package com.cpe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cpe.ui.screens.MainScaffold
import com.cpe.ui.screens.auth_screen.AuthScreen
import com.cpe.ui.screens.auth_screen.AuthViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val startDestination = if (authViewModel.isUserLoggedIn())
        Screen.MainScaffold.route else Screen.AuthScreen.route

    NavHost(
        navController = navController,
        startDestination = startDestination,
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