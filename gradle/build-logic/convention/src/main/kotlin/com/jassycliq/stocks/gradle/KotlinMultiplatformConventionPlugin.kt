package com.jassycliq.stocks.gradle

import com.jassycliq.stocks.gradle.configure.configureJava
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.multiplatform")
        }

        extensions.configure<KotlinMultiplatformExtension> {
            applyDefaultHierarchyTemplate()
            jvm()
            if (pluginManager.hasPlugin("com.android.library")) {
                androidTarget()
            }
            iosArm64()
            iosSimulatorArm64()

            configureJava()

            //region All targets
            targets.configureEach {
                compilations.configureEach {
                    compileTaskProvider.configure {
                        compilerOptions {
                            freeCompilerArgs.add("-Xexpect-actual-classes")
                        }
                    }
                }
            }
            //endregion

            //region Kotlin Native (iOS) target
            targets.withType<KotlinNativeTarget>().configureEach {
                binaries.configureEach {
                    freeCompilerArgs += "-Xdisable-phases=RemoveRedundantCallsToStaticInitializersPhase" // https://youtrack.jetbrains.com/issue/KT-64508
                }
                compilations.configureEach {
                    compileTaskProvider.configure {
                        compilerOptions {
                            freeCompilerArgs.addAll(
                                "-Xadd-light-debug=enable",
                                "-opt-in=kotlinx.cinterop.ExperimentalForeignApi",
                                "-opt-in=kotlinx.cinterop.BetaInteropApi",
                                "-XXLanguage:+ImplicitSignedToUnsignedIntegerConversion", // https://kotlinlang.org/docs/whatsnew19.html#compiler-option-for-c-interop-implicit-integer-conversions
                                "-Xallocator=custom", // https://kotlinlang.org/docs/whatsnew19.html#preview-of-custom-memory-allocator
                            )
                        }
                    }
                }
            }
            //endregion
        }
    }
}
