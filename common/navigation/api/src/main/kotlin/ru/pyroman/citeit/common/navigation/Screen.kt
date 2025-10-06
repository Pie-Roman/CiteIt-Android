package ru.pyroman.citeit.common.navigation

sealed class Screen(
    val route: String,
) {

    data object Start : Screen(route = "start")
    data object Main : Screen(route = "main")
}