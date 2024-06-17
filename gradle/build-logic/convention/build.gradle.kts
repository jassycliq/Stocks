plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.composeCompiler.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.jassycliq.android.application"
            implementationClass = "com.jassycliq.stocks.gradle.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "com.jassycliq.android.library"
            implementationClass = "com.jassycliq.stocks.gradle.AndroidLibraryConventionPlugin"
        }
        register("kotlinAndroid") {
            id = "com.jassycliq.kotlin.android"
            implementationClass = "com.jassycliq.stocks.gradle.KotlinAndroidConventionPlugin"
        }
        register("kotlinMultiplatform") {
            id = "com.jassycliq.kotlin.multiplatform"
            implementationClass = "com.jassycliq.stocks.gradle.KotlinMultiplatformConventionPlugin"
        }
        register("composeMultiplatform") {
            id = "com.jassycliq.compose.multiplatform"
            implementationClass = "com.jassycliq.stocks.gradle.ComposeMultiplatformConventionPlugin"
        }
    }
}
