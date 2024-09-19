package com.example.pokedexcompose.core.network.di

import com.example.pokedexcompose.core.network.Dispatcher
import com.example.pokedexcompose.core.network.PokedexAppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal object DispatchersModule {

  @Provides
  @Dispatcher(PokedexAppDispatchers.IO)
  fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

  @Provides
  @Dispatcher(PokedexAppDispatchers.DEFAULT)
  fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

  @Provides
  @Dispatcher(PokedexAppDispatchers.UNCONFINED)
    fun providesUndefinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}
