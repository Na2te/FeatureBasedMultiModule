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

dependencies {
    // ApplicationExtension 등 Android 앱과 라이브러리의 Gradle에 접근하기 위한 plugin
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)

    compileOnly(libs.android.tools.common)

    compileOnly(libs.compose.gradlePlugin)
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

gradlePlugin {
    plugins {
        register("androidApplication") {
            // 간혹 Now In Android에서는 .asProvider().get으로 불러오는 경우도 있던데 도대체 무슨 차이인지 모르겠음
            id = libs.plugins.project.android.application.get().pluginId
            implementationClass = "com.na2te.convention.plugins.AndroidApplicationConventionPlugin"
        }

        register("androidCompose"){
            id = libs.plugins.project.android.compose.get().pluginId
            implementationClass = "com.na2te.convention.plugins.AndroidComposeConventionPlugin"
        }

        register("androidFeature"){
            id = libs.plugins.project.android.feature.get().pluginId
            implementationClass = "com.na2te.convention.plugins.AndroidFeatureConventionPlugin"
        }

        register("androidLibrary"){
            id = libs.plugins.project.android.library.get().pluginId
            implementationClass = "com.na2te.convention.plugins.AndroidLibraryConventionPlugin"
        }

        register("hilt"){
            id = libs.plugins.project.hilt.get().pluginId
            implementationClass = "com.na2te.convention.plugins.HiltConventionPlugin"
        }
    }
}
