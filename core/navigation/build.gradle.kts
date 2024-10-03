plugins {
    alias(libs.plugins.pokedexCompose.android.library)
    alias(libs.plugins.pokedexCompose.android.library.compose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.pokedexCompose.android.hilt)
    alias(libs.plugins.pokedexCompose.spotless)
}

android {
    namespace = "com.example.pokedexcompose.core.navigation"
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.androidx.core)
    implementation(libs.kotlinx.coroutines.android)
    api(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.runtime)

    implementation(libs.kotlinx.serialization.json)
}
