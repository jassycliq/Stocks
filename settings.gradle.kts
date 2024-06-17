rootProject.name = "Stocks"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("gradle/build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

//region Main modules
include(":androidApp")
include(":shared")
//endregion

include(":api:cash:stocks")
include(":common:ui:compose")
include(":common:ui:screens")
include(":common:utils")
include(":core")
include(":data:models")
include(":data:stock")
include(":domain")
include(":ui:root")
include(":ui:stock:list")
