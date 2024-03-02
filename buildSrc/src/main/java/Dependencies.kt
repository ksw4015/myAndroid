object Versions {
    const val kotlin = "1.9.22"
    const val appCompat = "1.6.1"
    const val hilt = "2.51"
    const val retrofit = "2.9.0"
    const val coroutines = "1.6.4"
    const val constraintLayout = "2.1.4"
    const val material = "1.9.0"
    const val lifecycle = "2.7.0"
    const val core = "1.10.1"
    const val activity = "1.8.2"
    const val fragment = "1.6.2"
}

object Deps {

    object Kotlin {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val convert_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val testing = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    }

    object Lifecycle {
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

}