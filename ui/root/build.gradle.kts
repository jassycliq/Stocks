plugins {
    id("com.jassycliq.android.library")
    id("com.jassycliq.kotlin.multiplatform")
    id("com.jassycliq.compose.multiplatform")
}

android {
    namespace = "com.jassycliq.stocks.ui.root"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core)
                implementation(projects.common.ui.compose)
                implementation(projects.common.ui.screens)

                implementation(libs.circuit.overlay)
                implementation(libs.circuitx.gestureNavigation)

                implementation(compose.foundation)
                implementation(compose.materialIconsExtended)
            }
        }
    }
}
