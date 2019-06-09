plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Versions.targetSdk)

    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = 1
        versionName = "1.0"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(Dependencies.kotlin.stdlib)

    implementation(Dependencies.retrofit.library)
    implementation(Dependencies.retrofit.rxJava)
    implementation(Dependencies.retrofit.gson)
    implementation(Dependencies.okhttp.library)
    implementation(Dependencies.okhttp.logging)
    implementation(Dependencies.picasso)

    implementation(Dependencies.androidx.core)
    implementation(Dependencies.androidx.material)
    implementation(Dependencies.androidx.liveData.extensions)
}
