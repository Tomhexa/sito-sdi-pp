plugins {
    // Use double quotes for plugin IDs
    id("com.android.application")
    id("org.jetbrains.kotlin.android") // Assuming your Kotlin Android plugin is applied
    // Apply the Compose Compiler plugin WITHOUT specifying a version here,
    // as it seems version 2.0.21 is already on the classpath (likely from project-level or catalog)
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.rovetta" // Make sure this matches your actual package name
    compileSdk = 35 // <--- MODIFIED HERE

    defaultConfig {
        applicationId = "com.example.rovetta" // Make sure this matches
        minSdk = 28
        targetSdk = 34 // Consider changing this to 35 as well
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    // You might need to explicitly set composeOptions.kotlinCompilerExtensionVersion
    // if it's not correctly inferred or managed by your BOM and plugin setup,
    // especially if you have a mismatch between Kotlin version and Compose library versions.
    // Check the Compose to Kotlin Compatibility map: https://developer.android.com/jetpack/androidx/releases/compose-kotlin
    // Example:
    // composeOptions {
    //     kotlinCompilerExtensionVersion = "1.5.3" // Use the version compatible with your Kotlin & Compose libs
    // }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Using the specific Compose BOM you defined.
    // This helps manage versions of Compose libraries consistently.
    val composeBom = platform("androidx.compose:compose-bom:2024.06.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Individual Compose dependencies (versions will be managed by the BOM)
    implementation(libs.androidx.ui) // Equivalent to "androidx.compose.ui:ui"
    implementation(libs.androidx.ui.graphics) // Equivalent to "androidx.compose.ui:ui-graphics"
    implementation(libs.androidx.ui.tooling.preview) // Equivalent to "androidx.compose.ui:ui-tooling-preview"
    implementation(libs.androidx.material3) // Equivalent to "androidx.compose.material3:material3"

    // Navigation
    implementation(libs.androidx.navigation.runtime.ktx) // Or specific navigation artifacts
    implementation(libs.androidx.navigation.compose)

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.5.0") // Check for the latest stable version

    implementation("androidx.palette:palette-ktx:1.0.0")

    // Desugaring dependency
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.3")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Test dependencies for Compose (versions managed by BOM)
    androidTestImplementation(libs.androidx.ui.test.junit4) // Equivalent to "androidx.compose.ui:ui-test-junit4"
    debugImplementation(libs.androidx.ui.tooling) // Equivalent to "androidx.compose.ui:ui-tooling"
    debugImplementation(libs.androidx.ui.test.manifest) // Equivalent to "androidx.compose.ui:ui-test-manifest"
}