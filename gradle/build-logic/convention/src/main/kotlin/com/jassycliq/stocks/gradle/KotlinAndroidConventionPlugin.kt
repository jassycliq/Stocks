package com.jassycliq.stocks.gradle

import com.jassycliq.stocks.gradle.configure.configureJava
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinAndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }

            configureJava()
        }
    }
}
