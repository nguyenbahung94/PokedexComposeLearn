plugins {
    alias(libs.plugins.pokedexCompose.android.library)
    alias(libs.plugins.pokedexCompose.android.hilt)
    alias(libs.plugins.pokedexCompose.spotless)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.pokedexcompose.core.network"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(projects.core.model)
    testImplementation(projects.core.test)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    // network
    implementation(libs.sandwich)
    implementation(platform(libs.retrofit.bom))
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.retrofitBundle)
    testImplementation(libs.okhttp.mockwebserver)
    testImplementation(libs.androidx.arch.core.testing)

    // json parsing
    implementation(libs.kotlinx.serialization.json)
}
