plugins {
    id("com.jassycliq.android.application")
    id("com.jassycliq.kotlin.android")
    id("com.jassycliq.compose.multiplatform")
}

android {
    namespace = "com.jassycliq.stocks.android"

    defaultConfig {
        applicationId = "com.jassycliq.stocks"
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        compose = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

composeCompiler {
    enableStrongSkippingMode = true
}

dependencies {
    implementation(projects.shared)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    
    implementation(libs.androidx.compose.toolingPreview)
    debugImplementation(libs.androidx.compose.tooling)
}
