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
    packaging {
        resources {
            // Exclude one of the NOTICE.md files to avoid conflict
            excludes += "META-INF/NOTICE.md"
            excludes += "META-INF/LICENSE.md" // Optional if there are other similar conflicts
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
    testOptions {
        unitTests.all {
        }
    }
}
tasks.dokkaHtml {
    outputDirectory.set(buildDir.resolve("dokka"))
}
tasks.withType<Test> {
    useJUnitPlatform()
}



dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)

    // JUnit 4 for unit tests
    //testImplementation("junit:junit:4.13.2")

    // Robolectric for Android unit testing
    testImplementation("org.robolectric:robolectric:4.9.1")
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.testng) // Check for the latest version

    // Android test dependencies
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Dokka
    implementation("org.jetbrains.dokka:dokka-core:1.7.10")
    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    // Optional: AssertJ for fluent assertions (optional but helpful)
    testImplementation("org.assertj:assertj-core:3.24.2")


}