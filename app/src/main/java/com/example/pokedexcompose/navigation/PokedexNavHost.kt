package com.example.pokedexcompose.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.pokedexcompose.core.navigation.PokedexScreen

@Composable
fun PokedexNavHost(navHostController: NavHostController) {
  SharedTransitionLayout {
    NavHost(
      navController = navHostController,
      startDestination = PokedexScreen.Home,
    ) {
      pokedexNavigation()
    }
  }
}
