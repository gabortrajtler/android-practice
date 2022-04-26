plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {

    // Android plugin dependencies
    implementation("com.android.tools.build:gradle:7.1.3")

    // Kotlin plugin dependencies
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
}
