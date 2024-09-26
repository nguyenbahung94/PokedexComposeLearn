package com.example.pokedexcompose.feature.details

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.core.data.repository.details.DetailsRepository
import com.example.pokedexcompose.core.model.Pokemon
import com.example.pokedexcompose.core.model.PokemonInfo
import com.example.pokedexcompose.core.viewmodel.BaseViewModel
import com.example.pokedexcompose.core.viewmodel.ViewModelStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository,
    saveStateHandle: SavedStateHandle
) : BaseViewModel() {

    internal val uiState: ViewModelStateFlow<DetailsUiState> =  viewModelStateFlow(DetailsUiState.Loading)

    val pokemon = saveStateHandle.getStateFlow<Pokemon?>("pokemon", null)

    val pokemonInfo: StateFlow<PokemonInfo?> = pokemon.filterNotNull().flatMapLatest { pokemon ->
        detailsRepository.fetchPokemonInfo(
            name = pokemon.nameField.replaceFirstChar { it.lowercase() },
            onComplete = { uiState.tryEmit(key, DetailsUiState.Idle)},
            onError = { uiState.tryEmit(key, DetailsUiState.Error(it))}
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )
}


@Stable
internal sealed interface DetailsUiState {

    data object Idle : DetailsUiState

    data object Loading : DetailsUiState

    data class Error(val message: String?) : DetailsUiState
}
