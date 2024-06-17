plugins {
    id("com.jassycliq.kotlin.multiplatform")
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.serialization)
            api(libs.kotlinx.datetime)
        }
        commonTest.dependencies {
        }
    }
}
