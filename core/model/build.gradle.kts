plugins {
    alias(libs.plugins.pokedexCompose.android.library)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.pokedexCompose.spotless)
}

android {
    namespace = "com.example.pokedexcompose.core.model"

}

dependencies {

    // compose stable marker
    compileOnly(libs.compose.stable.marker)

    // Kotlin Serialization for Json
    implementation(libs.kotlinx.serialization.json)

    // kotlinx
    api(libs.kotlinx.immutable.collection)
}
