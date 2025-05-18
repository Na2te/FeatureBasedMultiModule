plugins {
    alias(libs.plugins.project.android.feature)
    alias(libs.plugins.project.android.library.compose)
}

android {
    namespace = "com.na2te.second"
}

dependencies {
    implementation(projects.core.domain)
    api(libs.androidx.material3)
}