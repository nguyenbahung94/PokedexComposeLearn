package com.example.pokedexcompose.core.designsystem.component

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.pokedexcompose.core.designsystem.theme.Theme
import com.example.pokedexcompose.designsystem.R


@Composable
fun AppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = Theme.colors.absoluteWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = Theme.colors.primary,
        ),
    )
}

@Preview
@Composable
private fun PokedexAppBarPreview() {
    Theme {
       AppBar()
    }
}
