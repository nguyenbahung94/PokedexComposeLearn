package com.example.pokedexcompose.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokedexcompose.core.database.entity.PokemonEntity
import com.example.pokedexcompose.core.database.entity.PokemonInfoEntity

@Database(
    entities = [PokemonEntity::class, PokemonInfoEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(value = [TypeResponseConverter::class, StatsResponseConverter::class])
abstract class PokedexDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonInfoDao(): PokemonInfoDao
}

