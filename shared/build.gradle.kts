import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.jassycliq.android.library")
    id("com.jassycliq.kotlin.multiplatform")
    id("com.jassycliq.compose.multiplatform")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.jassycliq.stocks"
}

dependencies {
    add("kspCommonMainMetadata", libs.kotlinInject.compiler)
    add("kspAndroid", libs.kotlinInject.compiler)
    add("kspIosArm64", libs.kotlinInject.compiler)
    add("kspIosSimulatorArm64", libs.kotlinInject.compiler)
}

kotlin {
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            api(projects.common.ui.compose)
            api(projects.core)
            api(projects.data.stock)
            api(projects.domain)
            api(projects.ui.root)
            api(projects.ui.stock.list)

            api(compose.runtime)
            api(compose.foundation)
            api(compose.material)
            api(compose.ui)
            api(compose.components.resources)
            api(compose.components.uiToolingPreview)

            api(libs.kotlinInject.runtime)
        }
        androidMain.dependencies {
        }
        iosMain.dependencies {
        }
    }
}

ksp {
    arg("me.tatarka.inject.generateCompanionExtensions", "true")
}
