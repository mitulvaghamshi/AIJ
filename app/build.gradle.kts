plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "me.mitul.aij"
    compileSdk = 35

    defaultConfig {
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
        applicationId = "me.mitul.aij"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")
    implementation("com.readystatesoftware.sqliteasset:sqliteassethelper:2.0.1")
}
