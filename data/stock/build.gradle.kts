plugins {
    id("com.jassycliq.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.data.models)
            api(projects.core)
            implementation(projects.api.cash.stocks)
            implementation(libs.kotlinInject.runtime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
