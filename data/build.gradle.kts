plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.hilt)
}

android {
    namespace = "com.na2te.data"
}

dependencies {
    api(projects.network)
    implementation(projects.core.domain)
}