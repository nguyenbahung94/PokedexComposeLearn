package com.example.pokedexcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.example.pokedexcompose.core.navigation.AppComposeNavigator
import com.example.pokedexcompose.core.navigation.LocalComposeNavigator
import com.example.pokedexcompose.core.navigation.PokedexScreen
import com.example.pokedexcompose.ui.PokedexMain
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var composeNavigator: AppComposeNavigator<PokedexScreen>

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(
                LocalComposeNavigator provides composeNavigator
            ) {
                PokedexMain(composeNavigator = composeNavigator)
            }
        }
    }
}
