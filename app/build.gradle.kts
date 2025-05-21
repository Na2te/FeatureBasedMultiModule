plugins {
    // 기존의 andorid.application이 아니라 build-logic에 플러그인 관련 작업이 되어 있는 toml 내에 plugin 별칭으로 설정한 project.android.application이라는 커스텀 플러그인 적용
    // 물론 project.android.application이라는 커스텀 플러그인 관련 buil-logic 내의 관련 동작에는 android.application을 추가하는 작업이 되어 있음
    alias(libs.plugins.project.android.application)
    alias(libs.plugins.project.android.compose)
    alias(libs.plugins.project.hilt)

    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.na2te.featurebasedmultimodule"

    defaultConfig {
        applicationId = "com.na2te.featurebasedmultimodule"

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.feature.start)
    implementation(projects.feature.second)
    implementation(projects.core.domain)
    implementation(projects.core.designsystem)
    implementation(projects.data)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.kotlinx.serialization.json)

    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}