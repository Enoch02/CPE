package com.cpe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cpe.ui.screens.AboutScreen
import com.cpe.ui.screens.MainScaffold
import com.cpe.ui.screens.auth.AuthScreen
import com.cpe.ui.screens.auth.AuthViewModel
import com.cpe.ui.screens.gpcalculator.GpCalculatorScreen
import com.cpe.ui.screens.news.NewsScreen
import com.cpe.ui.screens.outlines.OutlineScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val startDestination = /*if (authViewModel.isUserLoggedIn())
        Screen.MainScaffold.route else Screen.AuthScreen.route*/
        Screen.MainScaffold.route

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

            composable(Screen.GpCalculatorScreen.route) {
                GpCalculatorScreen(navController = navController)
            }

            composable(Screen.OutlineScreen.route) {
                OutlineScreen(navController = navController)
            }

            composable(Screen.NewsScreen.route) {
                NewsScreen(navController = navController)
            }

            composable(Screen.AboutScreen.route) {
                AboutScreen(navController = navController)
            }
        }
    )
}