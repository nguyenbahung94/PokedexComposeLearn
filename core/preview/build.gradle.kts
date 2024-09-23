plugins {
    alias(libs.plugins.pokedexCompose.android.library)
    alias(libs.plugins.pokedexCompose.android.library.compose)
    alias(libs.plugins.pokedexCompose.android.hilt)
    alias(libs.plugins.pokedexCompose.spotless)
}

android {
    namespace = "com.example.pokedexcompose.core.preview"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)
    implementation(projects.core.model)
}
