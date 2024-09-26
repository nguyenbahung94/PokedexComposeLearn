package com.example.pokedexcompose.core.data.repository.details

import android.telecom.Call.Details
import androidx.annotation.VisibleForTesting
import com.example.pokedexcompose.core.database.PokemonInfoDao
import com.example.pokedexcompose.core.database.entity.mapper.asDomain
import com.example.pokedexcompose.core.database.entity.mapper.asEntity
import com.example.pokedexcompose.core.model.PokemonInfo
import com.example.pokedexcompose.core.network.Dispatcher
import com.example.pokedexcompose.core.network.PokedexAppDispatchers
import com.example.pokedexcompose.core.network.model.mapper.ErrorResponseMapper
import com.example.pokedexcompose.core.network.service.PokedexClient
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

@VisibleForTesting
class DetailsRepositoryImpl @Inject constructor(
    private val pokedexClient: PokedexClient,
    private val pokemonInfoDao: PokemonInfoDao,
    @Dispatcher(PokedexAppDispatchers.IO) private val ioDispatcher: kotlinx.coroutines.CoroutineDispatcher
): DetailsRepository {
    override fun fetchPokemonInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<PokemonInfo> = flow {
        val pokemonInfo = pokemonInfoDao.getPokemonInfo(name)

        if (pokemonInfo == null) {

            val response = pokedexClient.fetchPokemonInfo(name =  name)
            response.suspendOnSuccess {
                pokemonInfoDao.insertPokemonInfo(data.asEntity())
                emit(data)
            }.onError {
                map(ErrorResponseMapper) {
                    onError("[Code: $code]: $message")
                }
            }.onException { onError(message) }
        } else {
            emit(pokemonInfo.asDomain())
        }
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
