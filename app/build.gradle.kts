plugins {
    alias(libs.plugins.android.application)
//    kotlin("kapt")
}

android {
    namespace = "com.azad.productcatalogappairawatrf"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.azad.productcatalogappairawatrf"
        minSdk = 26
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Animation and Image Loading
    implementation("com.airbnb.android:lottie:6.1.0")
    implementation("io.coil-kt:coil:2.4.0")

    // Palette
    implementation("androidx.palette:palette-ktx:1.0.0")

    // Shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    // JSON and Networking
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Image Loading
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // Dimensions
    implementation("com.intuit.ssp:ssp-android:1.0.6")
    implementation("com.intuit.sdp:sdp-android:1.0.6")

    // Room
    val roomVersion = "2.6.1"

    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

 //   kapt("androidx.room:room-compiler:$roomVersion")

    // Coroutine for Asyncronous operations
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Glide image loading
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    // Navigation Component
    implementation ("androidx.navigation:navigation-fragment-ktx:2.8.9")
    implementation ("androidx.navigation:navigation-ui-ktx:2.8.9")

    implementation ("com.airbnb.android:lottie:4.2.2")

    implementation ("com.intuit.sdp:sdp-android:1.1.1")
    implementation ("com.intuit.ssp:ssp-android:1.1.1")

    implementation ("com.airbnb.android:lottie:6.0.0")


//    // Hilt
//    implementation("com.google.dagger:hilt-android:2.51.1")
//    kapt("com.google.dagger:hilt-compiler:2.51.1")
//
//    implementation("io.coil-kt:coil:2.4.0")





//    // Hilt
//    implementation("com.google.dagger:hilt-android:2.51.1")
//    kapt("com.google.dagger:hilt-compiler:2.51.1")
//
//    implementation("io.coil-kt:coil:2.4.0")



}