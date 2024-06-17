plugins {
    id("com.jassycliq.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core)
                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.serialization)
                api(libs.kotlinx.datetime)
                api(libs.kotlinInject.runtime)
                api(libs.ktor.client.core)
                implementation(libs.ktor.client.json)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.contentNegotiation)
            }
        }
        jvmMain.dependencies {
            api(libs.ktor.client.okhttp)
            implementation(libs.okhttp.logging)
        }
        iosMain.dependencies {
            api(libs.ktor.client.darwin)
        }
    }
}
