// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.android.application) apply false
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.kotlin.android) apply false
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.hilt) apply false
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.kapt) apply false
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.ksp) apply false
}