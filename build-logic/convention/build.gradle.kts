plugins {
    `kotlin-dsl`
}

group = "com.example.pokedexcompose.buildlogic"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "pokedexCompose.android.application.compose"
            implementationClass = "plugins.AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "pokedexCompose.android.application"
            implementationClass = "plugins.AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "pokedexCompose.android.library.compose"
            implementationClass = "plugins.AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "pokedexCompose.android.library"
            implementationClass = "plugins.AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "pokedexCompose.android.feature"
            implementationClass = "plugins.AndroidFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "pokedexCompose.android.hilt"
            implementationClass = "plugins.AndroidHiltConventionPlugin"
        }
        register("spotless") {
            id = "pokedexCompose.spotless"
            implementationClass = "plugins.SpotlessConventionPlugin"
        }
    }
}
