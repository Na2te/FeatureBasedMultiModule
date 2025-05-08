package com.na2te.convention.configure

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
// = 연산자를 이용하여 Property 타입의 값을 설정할 수 있게 해주는 것
import org.gradle.kotlin.dsl.assign

inline fun <reified T : KotlinBaseExtension> Project.configureKotlin() = configure<T> {
    // Treat all Kotlin warnings as errors (disabled by default)
    // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
    // gradle.properties 파일에서 프로퍼티로 설정됐거나 or 명령에서 -PwarningsAsErros=true/false 꼴로 전달되었을 때 해당 프로퍼티를 읽어옴
    // 만약 설정된 게 없다면 기본 값으로 false를 사용
    val warningsAsErrors = providers.gradleProperty("warningsAsErrors").map {
        it.toBoolean()
    }.orElse(false)

    when (this) {
        is KotlinAndroidProjectExtension -> compilerOptions
        is KotlinJvmProjectExtension -> compilerOptions
        else -> TODO("Unsupported project extension $this ${T::class}")
    }.apply {
        jvmTarget = JvmTarget.JVM_11
        // warning 관련해서 설정된 값으로 allWarningsAsErrors를 처리
        allWarningsAsErrors = warningsAsErrors
        freeCompilerArgs.add(
            // Enable experimental coroutines APIs, including Flow
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )
    }
}