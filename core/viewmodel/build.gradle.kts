plugins {
    alias(libs.plugins.pokedexCompose.android.library)
    alias(libs.plugins.pokedexCompose.spotless)
}

android {
    namespace = "com.example.pokedexcompose.core.viewmodel"
}

dependencies {
    api(libs.androidx.lifecycle.viewModelCompose)
}
