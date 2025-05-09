package com.na2te.convention.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.na2te.convention.configure.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        with(target){
            apply(plugin = "project.android.library")
            apply(plugin = "project.hilt")

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }

}