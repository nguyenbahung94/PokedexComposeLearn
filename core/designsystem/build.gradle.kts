plugins {
    alias(libs.plugins.pokedexCompose.android.library)
    alias(libs.plugins.pokedexCompose.android.library.compose)
    alias(libs.plugins.pokedexCompose.spotless)
}

android {
    namespace = "com.example.pokedexcompose.designsystem"
}

dependencies {
    libs.apply {
        api(bundles.landscapistBundle)
        api(bundles.composeBundle)
    }
}
