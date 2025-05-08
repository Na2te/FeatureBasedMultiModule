package com.na2te.convention.configure

import com.android.build.api.dsl.CommonExtension
import com.na2te.convention.libs
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.dependencies

import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

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

    // ./gradlew assembleRelease -PenableComposeCompilerMetrics=true -PenableComposeCompilerReports=true 를 이용해서 성능 및 보고서 생성 가능

    extensions.configure<ComposeCompilerGradlePluginExtension> {
        fun Provider<String>.onlyIfTrue() = flatMap { provider { it.takeIf(String::toBoolean) } }
        fun Provider<*>.relativeToRootProject(dir: String) = map {
            isolated.rootProject.projectDirectory
                .dir("build")
                .dir(projectDir.toRelativeString(rootDir))
        }.map { it.dir(dir) }

        /*만약 gradlew를 통해 enableComposeCompilerMetrics라는 옵션이 들어오거면 메트릭 정보를 수집하여 저장함*/
        project.providers.gradleProperty("enableComposeCompilerMetrics").onlyIfTrue()
            .relativeToRootProject("compose-metrics")
            .let(metricsDestination::set)

        /*만약 gradlew를 통해 enableComposeCompilerReports 옵션이 들어오거면 리컴포지션 비용 등이 있는 리포트 파일들을 생성하여 저장함*/
        project.providers.gradleProperty("enableComposeCompilerReports").onlyIfTrue()
            .relativeToRootProject("compose-reports")
            .let(reportsDestination::set)

        stabilityConfigurationFiles
            .add(isolated.rootProject.projectDirectory.file("compose_compiler_config.conf"))
    }
}