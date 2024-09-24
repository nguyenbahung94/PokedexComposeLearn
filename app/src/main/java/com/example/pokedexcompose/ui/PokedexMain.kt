package com.example.pokedexcompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.example.pokedexcompose.core.designsystem.theme.PokedexTheme
import com.example.pokedexcompose.core.navigation.AppComposeNavigator
import com.example.pokedexcompose.core.navigation.PokedexScreen
import com.example.pokedexcompose.navigation.PokedexNavHost


@Composable
fun PokedexMain(composeNavigator: AppComposeNavigator<PokedexScreen>) {
  PokedexTheme {
    val navHostController = rememberNavController()

    LaunchedEffect(Unit) {
      composeNavigator.handleNavigationCommands(navHostController)
    }

    PokedexNavHost(navHostController = navHostController)
  }
}
