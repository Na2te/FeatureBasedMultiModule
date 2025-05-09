plugins {
    alias(libs.plugins.project.android.feature)
    alias(libs.plugins.project.android.library.compose)
}

android {
    namespace = "com.na2te.start"
}

dependencies {
    api(libs.androidx.material3)
}