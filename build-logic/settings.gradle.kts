/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

// 프로젝트가 의존하는 라이브러리들을 어떻게 가져올지를 설정하는 블록
dependencyResolutionManagement {
    repositories {
        // 구글 저장소에서 com.android, com.google, androidx.으로 시작하는 그룹 ID를 가진 라이브러리들은 여기서 찾도록 함
        // 이를 통해 빌드 속도 최적화
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        /*
        * 위의 그룹 ID를 제외한 나머지는 여기서 찾도록 함
        * */
        mavenCentral()
    }

    // 프로젝트에서 사용하는 라이브러리 버전들을 한 곳에서 관리할 수 있게 하는 버전 카탈로그 생성
    versionCatalogs {
        /*
        libs 라는 이름의 버전 카탈로그를 생성
        from(파일)을 통해 해당 파일의 내용을 가져와서 구성
        즉 toml 파일 가져와서 libs로 접근하겠다는 뜻
        */
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"
include(":convention")
