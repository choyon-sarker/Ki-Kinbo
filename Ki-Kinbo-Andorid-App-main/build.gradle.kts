buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Change AGP version from 8.6.0 to 8.5.0
        classpath("com.android.tools.build:gradle:8.5.0")
        classpath("com.google.gms:google-services:4.3.15") // Example Google plugin
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}