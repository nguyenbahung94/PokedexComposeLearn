package com.example.pokedexcompose.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.pokedexcompose.core.navigation.PokedexScreen
import com.example.pokedexcompose.feature.home.PokedexHome

context(SharedTransitionScope)
fun NavGraphBuilder.pokedexNavigation() {
  composable<PokedexScreen.Home> {
    PokedexHome(this)
  }

  composable<PokedexScreen.Details>(
    typeMap = PokedexScreen.Details.typeMap,
  ) {
   // PokedexDetails(this)
  }
}
