plugins {
    id("DevFestPlugin")
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        applicationId = "com.paligot.devfestlille"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(project(":agenda"))

    implementation(Dependencies.kotlin.stdlib)

    implementation(Dependencies.androidx.navigation.fragment)
    implementation(Dependencies.androidx.navigation.ui)
}
