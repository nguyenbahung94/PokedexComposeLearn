package com.example.pokedexcompose.core.data.di

import com.example.pokedexcompose.core.data.repository.details.DetailsRepository
import com.example.pokedexcompose.core.data.repository.details.DetailsRepositoryImpl
import com.example.pokedexcompose.core.data.repository.home.HomeRepository
import com.example.pokedexcompose.core.data.repository.home.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

  @Binds
  fun bindsMainRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

  @Binds
  fun bindsDetailsRepository(detailsRepositoryImpl: DetailsRepositoryImpl): DetailsRepository
}
