plugins {
    id("DevFestPlugin")
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
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
