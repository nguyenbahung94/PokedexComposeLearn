package com.example.pokedexcompose.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val pokedexAppDispatchers: PokedexAppDispatchers)

enum class PokedexAppDispatchers {
    IO, DEFAULT, UNCONFINED
}
