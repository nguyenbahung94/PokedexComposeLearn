package com.example.pokedexcompose.core.network.model.mapper

import com.example.pokedexcompose.core.network.model.PokemonErrorResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mappers.ApiErrorModelMapper
import com.skydoves.sandwich.message
import com.skydoves.sandwich.retrofit.statusCode


object ErrorResponseMapper : ApiErrorModelMapper<PokemonErrorResponse> {

  override fun map(apiErrorResponse: ApiResponse.Failure.Error): PokemonErrorResponse {
    return PokemonErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
  }
}
