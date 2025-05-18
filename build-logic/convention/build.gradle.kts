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
            /*
            만약 ~~.x 플러그인이 있는데, ~~.x.x 같은 보기에 형제가 아니라 자식 같은 플러그인이 있을 때 그냥 .get()으로 하면
            Unresolved reference. None of the following candidates is applicable because of receiver type mismatch
            이런 에러가 뜨는 듯
            이 때 .asProvider().get() 하면 에러 없이 잘 된다
            register로 자식 관계처럼 보이는 플러그인을 등록하고 말고의 여부랑 상관없이 그냥 toml 파일에 그런 관계가 있는지로 결정되는 듯
            부모를 .asProvider()~로 부르면
            자식은 그냥 .get()으로 불러도 별 문제가 없는 것 확인
            */
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
            id = libs.plugins.project.android.library.asProvider().get().pluginId
            implementationClass = "com.na2te.convention.plugins.AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose"){
            id = libs.plugins.project.android.library.compose.get().pluginId
            implementationClass = "com.na2te.convention.plugins.AndroidLibraryComposeConventionPlugin"
        }

        register("hilt"){
            id = libs.plugins.project.hilt.get().pluginId
            implementationClass = "com.na2te.convention.plugins.HiltConventionPlugin"
        }

        register("library"){
            id = libs.plugins.project.jvm.library.get().pluginId
            implementationClass = "com.na2te.convention.plugins.JvmLibraryConventionPlugin"
        }

    }
}
