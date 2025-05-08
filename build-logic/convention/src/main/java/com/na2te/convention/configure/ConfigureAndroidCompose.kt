package com.na2te.convention.configure

import com.android.build.api.dsl.CommonExtension
import com.na2te.convention.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

import org.gradle.kotlin.dsl.configure

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        buildFeatures{
            // 컴포즈 사용 활성화
            compose = true
        }

        dependencies {
            // compose의 버전 관리를 도와주는 platform을 등록하고 Compose의 UI는는 꼭 봐야하니까 이것만 추가

            // 나머지는 필요시 직접 imple 해서 사용
            val bom = libs.findLibrary("androidx-compose-bom").get()
            "implementation"(platform(bom))
            "androidTestImplementation"(platform(bom))
            "implementation"(libs.findLibrary("androidx-ui").get())
            "implementation"(libs.findLibrary("androidx-ui-graphics").get())
            "debugImplementation"(libs.findLibrary("androidx-ui-tooling").get())
            "implementation"(libs.findLibrary("androidx-ui-tooling-preview").get())
        }
    }

}