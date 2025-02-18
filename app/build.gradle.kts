import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.newsapp"
    compileSdk = 35

    val localProperties = Properties().apply {
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            load(localPropertiesFile.inputStream())
        }
    }
    fun getEnvOrLocalProperty(envKey: String): String? {
        return System.getenv(envKey) ?: localProperties.getProperty(envKey)
    }

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", "\"${project.properties["API_KEY"]}\"")
    }

    signingConfigs {
        create("release") {
            keyAlias = getEnvOrLocalProperty("KEY_ALIAS")
            keyPassword = getEnvOrLocalProperty("KEY_PASSWORD")
            storeFile = file(getEnvOrLocalProperty("STORE_FILE") ?: "")
            storePassword = getEnvOrLocalProperty("KEY_STORE_PASSWORD")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
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
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.navigation.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // image loading
    implementation(libs.coil)

    // retrofit
    implementation(libs.squareup.retrofit2)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.squareup.okhttp3)
    implementation(libs.squareup.logging.interceptor)
    implementation(libs.squareup.retrofit2.gson)
    implementation(kotlin("script-runtime"))

    // Pagination
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    // hilt for injection
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)

}