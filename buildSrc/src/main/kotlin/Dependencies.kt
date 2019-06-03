object Versions {
    val targetSdk = 28
    val minSdk = 17

    val androidPlugin = "3.4.0"

    val core = "1.2.0-alpha01"
    val appCompat = "1.0.2"
    val material = "1.1.0-alpha02"
    val constraint = "1.1.3"
    val recyclerview = "1.0.0"
    val navigation = "2.1.0-alpha03"
    val livedata = "2.1.0-beta01"
    val fragment = "1.1.0-alpha08"

    val retrofit = "2.5.0"
    val okhttp = "3.12.1"
    val picasso = "2.71828"
    val timber = "4.7.1"
    val rxjava = "2.2.6"
    val rxandroid = "2.1.0"

    val kotlin = "1.3.31"
}

object Dependencies {
    val androidPlugin = "com.android.tools.build:gradle:${Versions.androidPlugin}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    val androidx = AndroidX
    val retrofit = Retrofit
    val okhttp = OkHttp
}

object AndroidX {
    val core = "androidx.core:core-ktx:${Versions.core}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val material = "com.google.android.material:material:${Versions.material}"
    val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    val recycler = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    val liveData = LiveData
    val navigation = Navigation
}

object Navigation {
    val plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object LiveData {
    val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.livedata}"
    val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.livedata}"
    val ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.livedata}"
    val rx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.livedata}"
}

object Retrofit {
    val library = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
}

object OkHttp {
    val library = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
}