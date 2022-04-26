plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "io.supercharge.compose.part5"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {
    coreLibraryDesugaring(Libraries.Android.jdk8)

    implementation(Libraries.Kotlin.Coroutines.core)
    implementation(Libraries.Kotlin.Coroutines.android)

    compileOnly(Libraries.AndroidX.annotation)
    implementation(Libraries.AndroidX.appcompat)
    implementation(Libraries.AndroidX.Ktx.core)
    implementation(Libraries.AndroidX.Ktx.viewModel)

    implementation(Libraries.AndroidX.Ui.material)
    implementation(Libraries.AndroidX.Ui.activity)

    implementation(Libraries.Compose.ui)
    implementation(Libraries.Compose.toolingPreview)
    debugImplementation(Libraries.Compose.tooling)
    implementation(Libraries.Compose.foundation)

    implementation(Libraries.Compose.activity)
    implementation(Libraries.Compose.viewModel)
    implementation(Libraries.Compose.viewBinding)

    implementation(Libraries.Compose.Ui.material)

    androidTestImplementation(Libraries.Test.junit)
    androidTestImplementation(Libraries.Test.Android.junit)
    androidTestImplementation(Libraries.Test.Android.core)
    androidTestImplementation(Libraries.Test.Android.archCore)
    androidTestImplementation(Libraries.Test.Android.runner)
    androidTestImplementation(Libraries.Test.Android.rules)
    androidTestImplementation(Libraries.Test.Android.uiautomator)

    androidTestUtil(Libraries.Test.Android.orchestrator)

    androidTestImplementation(Libraries.Test.Kotlin.junit)
    androidTestImplementation(Libraries.Test.Kotlin.coroutines)

    androidTestImplementation(Libraries.Test.Espresso.core)
    androidTestImplementation(Libraries.Test.Espresso.contrib)
    androidTestImplementation(Libraries.Test.Espresso.intents)

    androidTestImplementation(Libraries.Test.truth)
    androidTestImplementation(Libraries.Test.Android.truth)

    androidTestImplementation(Libraries.Test.Compose.junit)
    debugImplementation(Libraries.Test.Compose.manifest)
}
