plugins {
    alias(libs.plugins.android.test)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.pokedexCompose.spotless)
}

android {
    namespace = "com.example.pokedexcompose.baselineprofile"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = 24
        targetSdk = 35
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    targetProjectPath = ":app"

    testOptions.managedDevices.devices {
        maybeCreate<com.android.build.api.dsl.ManagedVirtualDevice>("pixel6api31").apply {
            device = "Pixel 6"
            apiLevel = 31
            systemImageSource = "aosp"
        }
    }

}

kotlin {
    jvmToolchain(17) // Set the same JVM version for Kotlin
}

// This is the configuration block for the Baseline Profile plugin.
// You can specify to run the generators on a managed devices or connected devices.
baselineProfile {
    managedDevices += "pixel6api31"
    useConnectedDevices = false
}

dependencies {
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.junit)
    implementation(libs.androidx.espresso)
    implementation(libs.uiautomator)
    implementation(libs.macrobenchmark)
    implementation(libs.profileinstaller)
}

androidComponents {
    onVariants { v ->
        val artifactsLoader = v.artifacts.getBuiltArtifactsLoader()
        v.instrumentationRunnerArguments.put("targetAppId",
            v.testedApks.map { artifactsLoader.load(it)?.applicationId.toString() })
    }
}
