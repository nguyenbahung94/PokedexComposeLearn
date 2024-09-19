package com.example.pokedexcompose.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokedexcompose.core.model.PokemonInfo

@Entity
data class PokemonInfoEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<PokemonInfo.TypeResponse>,
    val exp: Int,
    val stats: List<PokemonInfo.StatsResponse>,
)

