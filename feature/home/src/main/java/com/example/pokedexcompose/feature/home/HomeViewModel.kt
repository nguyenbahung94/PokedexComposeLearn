package com.example.pokedexcompose.feature.home

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.core.data.repository.home.HomeRepository
import com.example.pokedexcompose.core.model.Pokemon
import com.example.pokedexcompose.core.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): BaseViewModel() {
    internal val uiState = viewModelStateFlow<HomeUiState>(HomeUiState.Loading)

    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val pokemonList: StateFlow<List<Pokemon>> = pokemonFetchingIndex.flatMapLatest { page ->
        homeRepository.fetchPokemonList(
            page = page,
            onStart = { uiState.value = HomeUiState.Loading },
            onComplete = { uiState.value = HomeUiState.Idle },
            onError = { uiState.value = HomeUiState.Error(it) }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun fetchNextPokemonListPage() {
        pokemonFetchingIndex.value++
    }
}


@Stable
internal sealed interface HomeUiState {

    data object Idle : HomeUiState

    data object Loading : HomeUiState

    data class Error(val message: String?) : HomeUiState
}
