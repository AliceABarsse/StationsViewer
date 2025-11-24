plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    id("com.autonomousapps.dependency-analysis")
}

apply(from = "../gradle/scripts/jacoco.gradle")

android {
    namespace = "com.example.stationsviewer"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.stationsviewer"
        minSdk = 24
        targetSdk = 36
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.koin.android)
    implementation(libs.koin.core)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)
    implementation(libs.kotlinx.serialization.json)
    implementation(platform(libs.androidx.compose.bom))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":features"))

    testImplementation(libs.junit)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit4)
    testImplementation(libs.koin.android.test)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
  implementation("androidx.activity:activity:1.12.0")
  implementation("androidx.compose.foundation:foundation-layout:1.9.5")
  implementation("androidx.compose.runtime:runtime:1.9.5")
  androidTestImplementation("androidx.test:monitor:1.8.0")
  androidTestRuntimeOnly("androidx.test:runner:1.7.0")
  testRuntimeOnly("androidx.work:work-runtime:2.9.1")
  androidTestImplementation(libs.junit)
  testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:2.0.21")
  androidTestRuntimeOnly(libs.coroutines.test)

    // Test DATA TODO remove
    implementation(testFixtures(project(":core:model")))

}