package ru.pyroman.citeit.common.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.pyroman.citeit.feature.main.view.MainView
import ru.pyroman.citeit.feature.start.view.StartView

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Start.route,
        enterTransition = { slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Start,
            animationSpec = tween(300)
        ) },
        exitTransition = { slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Start,
            animationSpec = tween(300)
        ) },
    ) {
        composable(route = Screen.Start.route) {
            BackHandler {}

            StartView(
                navController = navController,
            )
        }
        composable(route = Screen.Main.route) {
            BackHandler {}

            MainView(
                navController = navController,
            )
        }
    }
}