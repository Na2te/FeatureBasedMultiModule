plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.hilt)
    alias(libs.plugins.secrets)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.na2te.data"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization.convertor)
    implementation(libs.okhttp.logging.interceptor)
}