package com.example.pokedexcompose.core.data.repository.details

import com.example.pokedexcompose.core.model.PokemonInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeDetailsRepository : DetailsRepository {

    override fun fetchPokemonInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<PokemonInfo> = flowOf()
}

