plugins {
    // Gradle 플러그인, 빌드 스크립트를 Kotlin 언어로 작성할 수 있게 제공하는 Gradle 플러그인
    `kotlin-dsl`
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

// Gradle 빌드 스크립트에서 특정 작업을 구성하거나 새로운 작업을 정의할 때 사용되는 블록
tasks {
    // validatePlugins의 작업에 추가적으로 설정
    validatePlugins {
        // 플러그인 유효성 검사를 더 엄격하게 수행하도록 설정
        enableStricterValidation = true
        // warning이 발생해도 빌드를 실패시키도록 설정
        failOnWarning = true
    }
}

