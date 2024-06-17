plugins {
    id("com.jassycliq.android.library")
    id("com.jassycliq.kotlin.multiplatform")
    id("com.jassycliq.compose.multiplatform")
}

android {
    namespace = "com.jassycliq.stocks.ui.stock.list"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core)
                implementation(projects.common.ui.compose)
                implementation(projects.common.ui.screens)
                implementation(projects.domain)

                implementation(libs.circuit.retained)
                implementation(libs.circuit.overlay)
                implementation(libs.circuitx.gestureNavigation)
                implementation(libs.circuitx.overlays)

                implementation(libs.kotlinx.datetime)

                implementation(compose.foundation)
                implementation(compose.materialIconsExtended)
            }
        }
    }
}
