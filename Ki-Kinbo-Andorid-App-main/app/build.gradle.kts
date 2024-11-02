plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id("org.jetbrains.dokka") version "1.9.0"
}

android {
    namespace = "com.devdroid.kikinbo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.devdroid.kikinbo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources {
            excludes += "META-INF/NOTICE.md"
            excludes += "META-INF/LICENSE.md" // Optional, if similar conflicts occur
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.dokkaHtml.configure { outputDirectory.set(file("../documentation/html")) }

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)

    // JUnit 5 dependencies
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation(libs.testng)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")

    // Mockito dependencies for JUnit 5
    testImplementation("org.mockito:mockito-core:4.0.0")
    testImplementation("org.mockito:mockito-inline:4.0.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0") // Mockito-Kotlin for cleaner syntax

    // AndroidX Test libraries
    //androidTestImplementation(libs.androidx.test.ext.junit) // For AndroidX JUnit extensions
    //androidTestImplementation(libs.androidx.test.espresso.espressoCore) // For Espresso tests
}
