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
