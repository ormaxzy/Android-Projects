plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.cityweather"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cityweather"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // AndroidX:
    implementation(libs.androidx.core.ktx.v190)
    implementation(libs.androidx.appcompat.v151)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.navigation.ui.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.cardview)
    implementation(libs.material)

    // MVVM:
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Dagger:
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    // Room:
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Retrofit:
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // OkHttp:
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Gson:
    implementation(libs.gson)

    // RxJava:
    implementation(libs.rxjava)

    // ConstraintLayout:
    implementation(libs.androidx.constraintlayout)

    // Testing:
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
