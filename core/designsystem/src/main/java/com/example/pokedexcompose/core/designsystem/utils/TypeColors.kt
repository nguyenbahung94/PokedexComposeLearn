package com.example.pokedexcompose.core.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.pokedexcompose.core.designsystem.theme.Theme

@Composable
fun getTypeColor(type: String): Color {
    return when (type) {
        "fighting" -> Theme.colors.fighting
        "flying" -> Theme.colors.flying
        "poison" -> Theme.colors.poison
        "ground" -> Theme.colors.ground
        "rock" -> Theme.colors.rock
        "bug" -> Theme.colors.bug
        "ghost" -> Theme.colors.ghost
        "steel" -> Theme.colors.steel
        "fire" -> Theme.colors.fire
        "water" -> Theme.colors.water
        "grass" -> Theme.colors.grass
        "electric" -> Theme.colors.electric
        "psychic" -> Theme.colors.psychic
        "ice" -> Theme.colors.ice
        "dragon" -> Theme.colors.dragon
        "fairy" -> Theme.colors.fairy
        "dark" -> Theme.colors.dark
        else -> Theme.colors.gray21
    }
}

