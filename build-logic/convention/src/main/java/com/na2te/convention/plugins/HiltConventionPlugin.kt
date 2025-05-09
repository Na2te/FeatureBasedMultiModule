package com.na2te.convention.plugins

import com.android.build.gradle.api.AndroidBasePlugin
import com.na2te.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        with(target){
            apply(plugin = "com.google.devtools.ksp")
            // ksp는 플러그인 상관 없이 적용
            dependencies {
                "ksp"(libs.findLibrary("hilt.compiler").get())
            }
            /*
            pluginManager.withPlugin(){}은 () 안에 있는 플러그인이 적용됐을 때만 내부 블록을 실행함
            즉 android 관련이면 hilt.android 의존성을
            아니면 android 관련 의존성이 없는 hilt-core 라이브러리를 추가
            */
            // Add support for Jvm Module, base on org.jetbrains.kotlin.jvm
            pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
                dependencies {
                    "implementation"(libs.findLibrary("hilt.core").get())
                }
            }

            /** Add support for Android modules, based on [AndroidBasePlugin] */
            pluginManager.withPlugin("com.android.base") {
                apply(plugin = "dagger.hilt.android.plugin")
                dependencies {
                    "implementation"(libs.findLibrary("hilt.android").get())
                }
            }
        }
    }

}