package com.na2te.convention.configure

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

/**
 * 안드로이드 관련 모듈 공통 모듈 설정을 진행하기 위한 확장 함수
 * 앱, 라이브러리 공통된 로직을 처리
 * */
internal fun Project.configureAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 35

        defaultConfig {
            minSdk = 21
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    // kotlinOption 관련해서 들어갈 내용들을 처리하기 위한 configure
    configureKotlin<KotlinAndroidProjectExtension>()
}