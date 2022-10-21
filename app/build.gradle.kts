plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "me.mitul.aij"
    compileSdk = 33

    defaultConfig {
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
        applicationId = "me.mitul.aij"
    }
    buildTypes {
        release {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    implementation("com.readystatesoftware.sqliteasset:sqliteassethelper:2.0.1")
}
