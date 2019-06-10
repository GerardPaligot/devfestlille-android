plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

dependencies {
    implementation(gradleApi())
    implementation("com.android.tools.build:gradle:3.6.0-alpha03")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.31")
}