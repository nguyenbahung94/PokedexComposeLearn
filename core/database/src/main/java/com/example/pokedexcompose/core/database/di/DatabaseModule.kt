package com.example.pokedexcompose.core.database.di

import android.app.Application
import androidx.room.Room
import com.example.pokedexcompose.core.database.PokedexDatabase
import com.example.pokedexcompose.core.database.PokemonDao
import com.example.pokedexcompose.core.database.PokemonInfoDao
import com.example.pokedexcompose.core.database.StatsResponseConverter
import com.example.pokedexcompose.core.database.TypeResponseConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

  @Provides
  @Singleton
  fun provideAppDatabase(
    application: Application,
    typeResponseConverter: TypeResponseConverter,
    statsResponseConverter: StatsResponseConverter,
  ): PokedexDatabase {
    return Room
      .databaseBuilder(application, PokedexDatabase::class.java, "Pokedex.db")
      .fallbackToDestructiveMigration()
      .addTypeConverter(typeResponseConverter)
      .addTypeConverter(statsResponseConverter)
      .build()
  }

  @Provides
  @Singleton
  fun providePokemonDao(appDatabase: PokedexDatabase): PokemonDao {
    return appDatabase.pokemonDao()
  }

  @Provides
  @Singleton
  fun providePokemonInfoDao(appDatabase: PokedexDatabase): PokemonInfoDao {
    return appDatabase.pokemonInfoDao()
  }

  @Provides
  @Singleton
  fun provideTypeResponseConverter(json: Json): TypeResponseConverter {
    return TypeResponseConverter(json)
  }
}
