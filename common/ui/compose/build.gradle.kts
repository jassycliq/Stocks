plugins {
    id("com.jassycliq.android.library")
    id("com.jassycliq.kotlin.multiplatform")
    id("com.jassycliq.compose.multiplatform")
}

android {
    namespace = "com.jassycliq.stocks.common.compose"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.common.ui.screens)
                api(libs.circuit.foundation)
                api(libs.circuitx.overlays)
                api(compose.material3)
            }
        }
    }
}
