package com.example.newsapp.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.newsapp.ui.screens.home.DetailScreen
import com.example.newsapp.ui.screens.home.HomeScreen
import com.example.newsapp.ui.screens.home.NewsItemsViewModel

private const val TRANSITION_DURATION = 300
fun NavGraphBuilder.newsListGraph(
    navController: NavController
) {
    // profile screens
    navigation(
        route = AppDestination.NewsListGraph.route,
        startDestination = NewsListGraphDestination.HomeScreen.route
    ) {
        composable(
            route = NewsListGraphDestination.HomeScreen.route,
            enterTransition = {EnterTransition.None},
            exitTransition = {ExitTransition.None }
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(AppDestination.NewsListGraph.route)
            }
            val  newsItemsViewModel= hiltViewModel<NewsItemsViewModel>(parentEntry)
            HomeScreen(
                newsItemsViewModel = newsItemsViewModel,
                navigateToDetailsScreen = {
                    navController.navigate(NewsListGraphDestination.DetailsScreen.route)
                }
            )
        }

        composable(
            route = NewsListGraphDestination.DetailsScreen.route,
            enterTransition = { slideInHorizontally(
                animationSpec = tween(TRANSITION_DURATION),
                initialOffsetX = { fullWidth -> fullWidth }
            ) },
            exitTransition = { ExitTransition.None }
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(AppDestination.NewsListGraph.route)
            }
            val newsItemsViewModel = hiltViewModel<NewsItemsViewModel>(parentEntry)
            DetailScreen(
                newsItemsViewModel = newsItemsViewModel,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class NewsListGraphDestination(val route: String) {
    data object HomeScreen: NewsListGraphDestination("HomeScreen")
    data object DetailsScreen: NewsListGraphDestination("DetailsScreen")
}