import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinAndroidTarget

plugins {
    id("com.jassycliq.android.library")
    id("com.jassycliq.kotlin.multiplatform")
    alias(libs.plugins.kotlinParcelize)
}

android {
    namespace = "com.jassycliq.stocks.common.ui.screens"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.core)
                api(libs.circuit.runtime)
            }
        }
    }

    targets.withType<KotlinAndroidTarget>().configureEach {
        compilations.configureEach {
            compileTaskProvider.configure {
                compilerOptions {
                    freeCompilerArgs.addAll(
                        "-P",
                        "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=com.jassycliq.stocks.common.ui.screens.CommonParcelize"
                    )
                }
            }
        }
    }
}
