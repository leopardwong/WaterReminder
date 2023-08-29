plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.wearos"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.wearos"
        minSdkPreview = "30"
        targetSdkPreview = "30"
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("com.google.android.gms:play-services-wearable:17.1.0")
    implementation("androidx.percentlayout:percentlayout:1.0.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.wear.compose:compose-material:1.1.2")
    implementation("androidx.wear.compose:compose-foundation:1.1.2")
    implementation ("androidx.wear.compose:compose-navigation:1.1.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.wear.tiles:tiles:1.1.0")
    implementation("androidx.wear.tiles:tiles-material:1.1.0")
    implementation("com.google.android.horologist:horologist-compose-tools:0.1.5")
    implementation("com.google.android.horologist:horologist-tiles:0.1.5")
    implementation("androidx.wear.watchface:watchface-complications-data-source-ktx:1.1.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation ("com.google.android.horologist:horologist-compose-layout:0.2.5")
    implementation ("androidx.compose.animation:animation:1.0.0")
    implementation ("androidx.compose.animation:animation-core:1.0.0")
    implementation ("androidx.wear:wear:1.1.0")
    implementation(project(mapOf("path" to ":commonCore")))

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.2")
}