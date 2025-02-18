package com.example.newsapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        route = AppDestination.MainGraph.route,
        startDestination = AppDestination.NewsListGraph.route
    ) {
        newsListGraph(navController = navController)
    }
}

sealed class AppDestination(val route: String) {
    data object MainGraph: AppDestination("MainGraph")
    data object NewsListGraph: AppDestination("NewsListGraph")
}
