package com.cpe.ui.navigation

enum class MainScaffoldScreens {
    HOME_SCREEN,
    SCHEDULE_SCREEN,
    RESOURCES_SCREEN,
    MORE_SCREEN,
}

sealed class Screen(val route: String) {
    object AuthScreen : Screen("auth_screen")
    object MainScaffold : Screen("main_scaffold")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}