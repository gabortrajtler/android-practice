object Versions {
    const val kotlin = "1.6.10"
    const val coroutines = "1.6.0"

    const val lifecycle = "2.4.0"
    const val activity = "1.4.0"

    const val compose = "1.1.1"
    const val composeCompiler = "1.1.1"

    const val testRunner = "1.4.0"
    const val espresso = "3.5.0-alpha05"
}

object Libraries {

    object Android {
        const val jdk8 = "com.android.tools:desugar_jdk_libs:1.1.5"
    }

    object Kotlin {

        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        }
    }

    object AndroidX {

        const val annotation = "androidx.annotation:annotation:1.2.0"
        const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        const val window = "androidx.window:window:1.0.0"

        object Ktx {
            const val core = "androidx.core:core-ktx:1.7.0"
            const val lifecycleScope = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        }

        object Ui {
            const val material = "com.google.android.material:material:1.5.0"
            const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
        }
    }

    object Compose {
        const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val viewBinding = "androidx.compose.ui:ui-viewbinding:${Versions.compose}"

        const val activity = "androidx.activity:activity-compose:${Versions.activity}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"

        object Ui {
            const val material = "androidx.compose.material:material:${Versions.compose}"
        }
    }

    object Test {
        const val junit = "junit:junit:4.13.2"
        const val truth = "com.google.truth:truth:1.1.3"
        const val robolectric = "org.robolectric:robolectric:4.7.3"

        object Android {
            const val junit = "androidx.test.ext:junit-ktx:1.1.3"
            const val core = "androidx.test:core-ktx:1.4.0"

            const val archCore = "androidx.arch.core:core-testing:2.1.0"

            const val runner = "androidx.test:runner:${Versions.testRunner}"
            const val rules = "androidx.test:rules:${Versions.testRunner}"
            const val orchestrator = "androidx.test:orchestrator:1.4.1"

            const val uiautomator = "androidx.test.uiautomator:uiautomator:2.2.0"

            const val truth = "androidx.test.ext:truth:1.4.0"
        }

        object Espresso {
            const val core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
            const val contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
            const val intents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
        }

        object Kotlin {
            const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        }

        object Compose {
            const val junit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
            const val manifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
        }
    }
}
