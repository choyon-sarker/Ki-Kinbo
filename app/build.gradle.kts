import com.android.build.api.dsl.Packaging

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id("org.jetbrains.dokka") version "1.9.20"
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

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // Define the packaging function here
    packaging {
        resources {
            excludes += listOf(
                "META-INF/LICENSE-notice.md",
                "META-INF/LICENSE.md",
                "META-INF/NOTICE.md",
                "META-INF/NOTICE.txt"
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

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.dokkaHtml.configure {
    outputDirectory.set(file("../documentation/html"))
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.core.ktx)

    // JUnit 5 dependencies for unit tests
        testImplementation(libs.junit.jupiter.api)
        testRuntimeOnly(libs.junit.jupiter.engine)

        // Android JUnit dependencies for instrumented tests
        androidTestImplementation(libs.junit.jupiter)

        // Android test dependencies
        androidTestImplementation(libs.androidx.espresso.core)

}
