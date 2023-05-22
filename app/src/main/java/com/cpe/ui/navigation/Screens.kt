package com.cpe.ui.navigation

enum class MainScaffoldScreens {
    HOME_SCREEN,
    SCHEDULE_SCREEN,

    /*RESOURCES_SCREEN,*/
    MORE_SCREEN,
}

sealed class Screen(val route: String) {
    object AuthScreen : Screen("auth_screen")
    object MainScaffold : Screen("main_scaffold")
    object GpCalculatorScreen : Screen("cgpa_calc_screen")
    object OutlineScreen : Screen("outline_screen")
    object NewsScreen : Screen("news_screen")
    object AboutScreen : Screen("about_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}