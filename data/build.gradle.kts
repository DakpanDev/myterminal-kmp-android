plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.moveagency.myterminal.data"
    compileSdk = 35
    buildFeatures.buildConfig = true

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val appId = providers.gradleProperty("myterminal.app.id").get()
        val appKey = providers.gradleProperty("myterminal.app.key").get()
        buildConfigField(
            type = "String",
            name = "AppId",
            value = "\"$appId\"",
        )
        buildConfigField(
            type = "String",
            name = "AppKey",
            value = "\"$appKey\"",
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

ksp {
    arg("KOIN_CONFIG_CHECK","true")
    arg("KOIN_DEFAULT_MODULE","false")
}

dependencies {
    implementation(project(":domain"))

    // Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Kotlin
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.collections.immutable)

    // Koin
    implementation(libs.koin.annotations)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    ksp(libs.koin.ksp.compiler)

    // Ktor
    implementation(libs.ktor.core)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization)

    // Room
    ksp(libs.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.room.kotlin.extensions)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}