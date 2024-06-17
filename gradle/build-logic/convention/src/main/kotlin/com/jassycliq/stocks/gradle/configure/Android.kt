package com.jassycliq.stocks.gradle.configure

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionConstraint
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.util.Optional

internal fun Project.configureAndroid() {
    extensions.configure<BaseExtension> {
        compileSdkVersion(libs.findVersion("android-compileSdk").asVersion())

        defaultConfig {
            minSdk = libs.findVersion("android-minSdk").asVersion()
            targetSdk = libs.findVersion("android-targetSdk").asVersion()

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            isCoreLibraryDesugaringEnabled = true
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        testOptions {
            if (this@configure is LibraryExtension) {
                // We only want to configure this for library modules
                targetSdk = libs.findVersion("android-targetSdk").asVersion()
            }

            unitTests {
                isIncludeAndroidResources = true
                isReturnDefaultValues = true
            }
        }
    }

    dependencies {
        "coreLibraryDesugaring"(libs.findLibrary("android.desugarJdkLibs").get())
    }
}

private fun Optional<VersionConstraint>.asVersion(): Int =
    get().toString().toInt()
