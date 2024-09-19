package com.example.pokedexcompose.core.data.repository.home

import com.example.pokedexcompose.core.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeHomeRepository : HomeRepository {
    override fun fetchPokemonList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<List<Pokemon>> = flowOf()
}

