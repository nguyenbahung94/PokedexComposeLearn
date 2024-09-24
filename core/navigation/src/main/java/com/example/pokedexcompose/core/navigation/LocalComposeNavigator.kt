package com.example.pokedexcompose.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

val LocalComposeNavigator: ProvidableCompositionLocal<AppComposeNavigator<PokedexScreen>> =
    compositionLocalOf {
        error(
            "No AppComposeNavigator provided! " +
                    "Make sure to wrap all usages of Pokedex components in PokedexTheme.",
        )
    }

/**
 * Retrieves the current [AppComposeNavigator] at the call site's position in the hierarchy.
 */
val currentComposeNavigator: AppComposeNavigator<PokedexScreen>
    @Composable
    @ReadOnlyComposable
    get() = LocalComposeNavigator.current

