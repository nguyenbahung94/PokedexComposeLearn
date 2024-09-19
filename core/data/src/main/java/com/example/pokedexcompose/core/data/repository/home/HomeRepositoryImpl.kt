package com.example.pokedexcompose.core.data.repository.home

import com.example.pokedexcompose.core.database.PokemonDao
import com.example.pokedexcompose.core.database.entity.mapper.asDomain
import com.example.pokedexcompose.core.database.entity.mapper.asEntity
import com.example.pokedexcompose.core.network.Dispatcher
import com.example.pokedexcompose.core.network.PokedexAppDispatchers
import com.example.pokedexcompose.core.network.service.PokedexClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val pokedexClient: PokedexClient,
    private val pokemonDao: PokemonDao,
    @Dispatcher(PokedexAppDispatchers.IO)
    private val ioDispatcher: CoroutineDispatcher
): HomeRepository {
    override fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ) = flow {
        var pokemons = pokemonDao.getPokemonList(page).asDomain()
        if (pokemons.isEmpty()) {
           val response = pokedexClient.fetchPokemonList(page = page)
           response.suspendOnSuccess {
               pokemons = data.results
               pokemons.forEach { pokemon -> pokemon.page = page }
               pokemonDao.insertPokemonList(pokemons.asEntity())
               emit(pokemonDao.getAllPokemonList(page).asDomain())
           }.onFailure {
               error(message())
           }
        } else {
            emit(pokemonDao.getAllPokemonList(page).asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
