plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

dependencies {
    implementation(gradleApi())
    implementation("com.android.tools.build:gradle:4.1.0-alpha06")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
}