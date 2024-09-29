package com.example.pokedexcompose.feature.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedexcompose.core.designsystem.component.PokedexProgressBar
import com.example.pokedexcompose.core.designsystem.theme.PokedexTheme
import com.example.pokedexcompose.core.preview.PokedexPreviewTheme

@Composable
internal fun PokemonStatusItem(
    modifier: Modifier = Modifier,
    pokedexStatus: PokedexStatus
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            modifier = Modifier
                .padding(start = 32.dp)
                .widthIn(min = 20.dp),
            text = pokedexStatus.type,
            color = PokedexTheme.colors.white70,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )

        PokedexProgressBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            progress = pokedexStatus.progress,
            color = pokedexStatus.color,
            label = pokedexStatus.label
        )

    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokemonStatusItemPreview() {
    PokedexPreviewTheme {
        PokemonStatusItem(
            pokedexStatus =  PokedexStatus(
                type = "HP",
                progress = 1f,
                color = PokedexTheme.colors.primary,
                label = "50/100"
            )
        )

    }

}
