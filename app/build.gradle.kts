import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
}

val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(rootProject.file("key.properties")))

android {
    namespace = "me.mitul.aij"
    compileSdk = 36

    defaultConfig {
        applicationId = "me.mitul.aij"

        minSdk = 35
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"

        signingConfig = signingConfigs.getByName("debug")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        buildConfig = true
    }

    signingConfigs {
        create("release") {
            enableV3Signing = true
            enableV4Signing = true

            keyAlias = keystoreProperties["keyAlias"].toString()
            keyPassword = keystoreProperties["keyPassword"].toString()
            storeFile = file(keystoreProperties["storeFile"]!!)
            storePassword = keystoreProperties["storePassword"].toString()
        }
    }

    buildTypes {
        getByName("debug") {
            multiDexEnabled = true

            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
        }

        getByName("release") {
            isMinifyEnabled = true
            multiDexEnabled = true
            isShrinkResources = true

            signingConfig = signingConfigs.getByName("release")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
        }
    }

    flavorDimensions += "version"

    productFlavors {
        create("free") {
            dimension = "version"
            applicationId = "me.mitul.aij.free"
            versionName = "1.0.0-free"
            // Syntax: type, name, value
            buildConfigField("String", "API_URL", "\"https://api.freeversion.com\"")
            buildConfigField("Boolean", "IS_PRO", "false")
        }

        create("pro") {
            dimension = "version"
            applicationId = "me.mitul.aij.pro"
            versionName = "1.0.0-pro"
            buildConfigField("String", "API_URL", "\"https://api.proversion.com\"")
            buildConfigField("Boolean", "IS_PRO", "true")
        }
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation("com.readystatesoftware.sqliteasset:sqliteassethelper:2.0.1")
}
