package com.example.pokedexcompose.core.preview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.pokedexcompose.core.designsystem.theme.PokedexTheme
import com.example.pokedexcompose.core.navigation.LocalComposeNavigator
import com.example.pokedexcompose.core.navigation.PokedexComposeNavigator

@Composable
fun PokedexPreviewTheme(
  content: @Composable SharedTransitionScope.(AnimatedVisibilityScope) -> Unit,
) {
  CompositionLocalProvider(
    LocalComposeNavigator provides PokedexComposeNavigator(),
  ) {
    PokedexTheme {
      SharedTransitionScope {
        AnimatedVisibility(visible = true, label = "") {
          content(this)
        }
      }
    }
  }
}
