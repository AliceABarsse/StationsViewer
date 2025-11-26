plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    id("com.autonomousapps.dependency-analysis")
}

apply(from = rootProject.file("gradle/scripts/jacoco.gradle"))

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

        getByName("debug") {
            isDebuggable = true
            enableAndroidTestCoverage = true
        }

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
        buildConfig = true // Used to access API KEY in network module
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.toString()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.compose.runtime)
    implementation(libs.koin.android)
    implementation(libs.koin.core)
    implementation(platform(libs.androidx.compose.bom))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":features"))
    testImplementation(project(":core:model"))

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.junit)
    androidTestRuntimeOnly(libs.coroutines.test)

    debugRuntimeOnly(libs.androidx.ui.test.manifest)
    debugImplementation(libs.androidx.ui.tooling)
}