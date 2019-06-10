plugins {
    id("DevFestPlugin")
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    dataBinding {
        isEnabled = true
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    implementation(project(":shared"))

    implementation(Dependencies.kotlin.stdlib)

    implementation(Dependencies.rxJava)
    implementation(Dependencies.rxAndroid)
    implementation(Dependencies.timber)
    implementation(Dependencies.circular)

    implementation(Dependencies.androidx.appCompat)
    implementation(Dependencies.androidx.material)
    implementation(Dependencies.androidx.constraint)
    implementation(Dependencies.androidx.recycler)
    implementation(Dependencies.androidx.fragment)
    implementation(Dependencies.androidx.liveData.extensions)
    kapt(Dependencies.androidx.liveData.compiler)
    implementation(Dependencies.androidx.navigation.fragment)
    implementation(Dependencies.androidx.navigation.ui)
}
