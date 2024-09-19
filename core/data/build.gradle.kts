plugins {
    alias(libs.plugins.pokedexCompose.android.library)
    alias(libs.plugins.pokedexCompose.android.hilt)
    alias(libs.plugins.pokedexCompose.spotless)
}

android {
    namespace = "com.example.pokedexcompose.core.data"
}

dependencies {
    // core modules
    api(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.core.database)
    testImplementation(projects.core.test)

    // kotlinx
    api(libs.kotlinx.immutable.collection)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    // network
    implementation(libs.sandwich)

    // unit test
    testImplementation(libs.junit)
    testImplementation(libs.turbine)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
}
