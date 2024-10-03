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

        api(libs.androidx.compose.runtime)
        api(libs.androidx.compose.ui)
        api(libs.androidx.compose.ui.tooling)
        api(libs.androidx.compose.ui.tooling.preview)
        api(libs.androidx.compose.animation)
        api(libs.androidx.compose.material3)
        api(libs.androidx.compose.foundation)
        api(libs.androidx.compose.foundation.layout)
    }
}
