plugins {
    id("com.android.application") version "8.9.2"
    id("org.jetbrains.kotlin.android") version "2.1.21"
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.21"
}

android {
    namespace = "com.footballtactics.tv"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.footballtactics.tv"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvmToolchain(17)
}

dependencyLocking {
    lockAllConfigurations()
}

dependencies {
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation("androidx.tv:tv-material:1.0.1")
}
