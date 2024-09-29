package com.example.pokedexcompose.feature.details

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokedexcompose.core.data.repository.details.FakeDetailsRepository
import com.example.pokedexcompose.core.designsystem.component.CircularProgress
import com.example.pokedexcompose.core.designsystem.component.PokedexText
import com.example.pokedexcompose.core.designsystem.component.pokedexSharedElement
import com.example.pokedexcompose.core.designsystem.theme.PokedexTheme
import com.example.pokedexcompose.core.designsystem.utils.getTypeColor
import com.example.pokedexcompose.core.model.Pokemon
import com.example.pokedexcompose.core.model.PokemonInfo
import com.example.pokedexcompose.core.navigation.boundsTransform
import com.example.pokedexcompose.core.navigation.currentComposeNavigator
import com.example.pokedexcompose.core.preview.PokedexPreviewTheme
import com.example.pokedexcompose.core.preview.PreviewUtils
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin

@Composable
fun SharedTransitionScope.PokedexDetails(
    animatedVisibilityScope: AnimatedVisibilityScope,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val uiState by detailsViewModel.uiState.collectAsStateWithLifecycle()
    val pokemon by detailsViewModel.pokemon.collectAsStateWithLifecycle()
    val pokemonInfo by detailsViewModel.pokemonInfo.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .testTag("PokedexDetails")
    ) {
        DetailsHeader(
            animatedVisibilityScope = animatedVisibilityScope,
            pokemon = pokemon,
            pokemonInfo = pokemonInfo
        )

        if (uiState == DetailsUiState.Idle && pokemonInfo != null) {
            DetailsInfo(pokemonInfo = pokemonInfo!!)
            DetailsStatus(pokemonInfo = pokemonInfo!!)
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgress()
            }
        }
    }
}

@Composable
private fun SharedTransitionScope.DetailsHeader(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemon: Pokemon?,
    pokemonInfo: PokemonInfo?
) {
    val composeNavigator = currentComposeNavigator
    var palette by remember { mutableStateOf<Palette?>(null) }
    val shape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomEnd = 64.dp,
        bottomStart = 64.dp
    )

    val backgroundBrush by palette.paletteBackgroundBrush()

    Box(
       modifier = Modifier
           .fillMaxWidth()
           .height(290.dp)
           .shadow(elevation = 9.dp, shape = shape)
           .background(brush = backgroundBrush, shape = shape)
    ) {
        Row(
            modifier = Modifier.padding(12.dp).statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 6.dp)
                    .clickable { composeNavigator.navigateUp() },
                painter = painterResource(id = com.example.pokedexcompose.designsystem.R.drawable.ic_arrow),
                tint = PokedexTheme.colors.absoluteWhite,
                contentDescription = null
            )

            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = pokemon?.name.orEmpty(),
                color = PokedexTheme.colors.absoluteWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        PokedexText(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .statusBarsPadding(),
            text = pokemonInfo?.getIdString().orEmpty(),
            previewText = "#001",
            color = PokedexTheme.colors.absoluteWhite,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )

        GlideImage(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
                .size(190.dp)
                .pokedexSharedElement(
                    isLocalInspectionMode = LocalInspectionMode.current,
                    state = rememberSharedContentState(key = "image-${pokemon?.name}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = boundsTransform,
                ),
            imageModel = { pokemon?.imageUrl },
            imageOptions = ImageOptions(contentScale = ContentScale.Inside),
            component = rememberImageComponent {
                +CrossfadePlugin()

                if (!LocalInspectionMode.current) {
                    +PalettePlugin(
                        imageModel = pokemon?.imageUrl,
                        useCache = true,
                        paletteLoadedListener = { palette = it },
                    )
                }
            },
            previewPlaceholder = painterResource(
                id = com.example.pokedexcompose.designsystem.R.drawable.pokemon_preview,
            ),
        )
    }

    PokedexText(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
            .pokedexSharedElement(
                isLocalInspectionMode = LocalInspectionMode.current,
                state = rememberSharedContentState(key = "name-${pokemon?.name}"),
                animatedVisibilityScope = animatedVisibilityScope,
                boundsTransform = boundsTransform,
            ),
        text = pokemon?.name.orEmpty(),
        previewText = "Bulbasaur",
        color = PokedexTheme.colors.black,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 36.sp,
    )
}

@Composable
private fun DetailsInfo(pokemonInfo: PokemonInfo) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp, Alignment.CenterHorizontally),
    ) {
        pokemonInfo.types.forEach { typeInfo ->
            Text(
                modifier = Modifier.background(
                        color = getTypeColor(type = typeInfo.type.name),
                        shape = RoundedCornerShape(64.dp),
                    ).padding(horizontal = 40.dp, vertical = 4.dp),
                text = typeInfo.type.name,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = PokedexTheme.colors.absoluteWhite,
                maxLines = 1,
                fontSize = 16.sp,
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        PokemonInfoItem(
            title = pokemonInfo.getWeightString(),
            content = stringResource(id = com.example.pokedexcompose.designsystem.R.string.weight),
        )

        PokemonInfoItem(
            title = pokemonInfo.getHeightString(),
            content = stringResource(id = com.example.pokedexcompose.designsystem.R.string.height),
        )
    }
}

@Composable
private fun DetailsStatus(
    pokemonInfo: PokemonInfo,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp, bottom = 16.dp),
        text = stringResource(id = com.example.pokedexcompose.designsystem.R.string.base_stats),
        textAlign = TextAlign.Center,
        color = PokedexTheme.colors.black,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp,
    )

    Column {
        pokemonInfo.toPokedexStatusList().forEach { pokemonStatus ->
            PokemonStatusItem(
                modifier = Modifier.padding(bottom = 12.dp),
                pokedexStatus = pokemonStatus,
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexDetailsStatusPreview() {
    PokedexPreviewTheme {
        DetailsStatus(
            pokemonInfo = PreviewUtils.mockPokemonInfo(),
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexDetailsInfoPreview() {
    PokedexPreviewTheme {
        DetailsInfo(pokemonInfo = PreviewUtils.mockPokemonInfo())
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedexDetailsPreview() {
    PokedexPreviewTheme {
        PokedexDetails(
            animatedVisibilityScope = it,
            detailsViewModel = DetailsViewModel(
                detailsRepository = FakeDetailsRepository(),
                saveStateHandle = SavedStateHandle(),
            ),
        )
    }
}
