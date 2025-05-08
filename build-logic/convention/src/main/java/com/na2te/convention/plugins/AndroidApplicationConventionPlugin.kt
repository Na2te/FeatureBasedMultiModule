package com.na2te.convention.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.na2te.convention.configure.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.android")
            /*
            애플리케이션 모듈의 빌드 설정을 담당
            com.android.application 플러그인이 적용된 모듈의 블록을 외한 확장
            https://developer.android.com/reference/tools/gradle-api/7.1/com/android/build/api/dsl/ApplicationExtension
            */
            extensions.configure<ApplicationExtension> {
                // android gradle 내의 android 블록 내에서 설정하는 것들을 설정
                defaultConfig.targetSdk = 35

                configureAndroid(this)

            }
        }
    }

}