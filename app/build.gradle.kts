plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.clindevstu.registropendientes"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.clindevstu.registropendientes"
        minSdk = 25
        targetSdk = 36
        versionCode = 1
        versionName = "2.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://script.google.com/macros/s/\"")
            buildConfigField("String", "REGISTROINTERNET_URL", "\"AKfycbyNC-FVx4XR3keplB4FwYlkOAfFymp4N6XG9PuczB8ykh97yAlrSsuntgrMMScqNRDV/\"")
            buildConfigField("String", "REGISTROAVERIAS_URL", "\"AKfycbzx-s5GFu9xAx663hCQezSubw1WOMgJ9Mna_x8I5tsaDKTgO9NUr4VYpYBJxC1FzccX/\"")
            buildConfigField("String", "REGISTROCAMARAS_URL", "\"AKfycbwNDwggmt0sdTskS00HNb3ysRFkf4WCEVxeOQmRsm7GjVrCQdq54sGDKg_gECWKWuYLqA/\"")
            buildConfigField("String", "REGISTRORECOJO_URL", "\"AKfycbzgQsD2Z7IVAiRzhC27VY6-K8W85lXknNDuxHQhrez23oINWXjknkfJSgQxF00WM_is/\"")
            buildConfigField("String", "APP_DATA_URL", "\"AKfycbxP6KHOj5G9Q5ljDPD_4udZTF0haBtSrayomf3plfCv60_rCEvEWjX01jB6DaNqvBvjDA/\"")
            buildConfigField("String", "USUARIOS_URL", "\"AKfycbxwF1jFg4fXg-lOVrCS2WOlqiIc_mNLeLXGmZy6Ad0Hn9_DoSWUuS2qmCZ8ShcTzN4JjQ/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.dagger.hilt.android)
    kapt(libs.hilt.compiler)


    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Gson
    implementation(libs.gson)


    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)



    // Lifecycle ViewModel (para usar viewModelScope, StateFlow en ViewModel, etc.)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Compose integration (si usas collectAsState())
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.datastore.preferences)

    implementation(libs.threetenabp)
}