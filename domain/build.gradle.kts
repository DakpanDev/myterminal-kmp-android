plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.ksp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

ksp {
    arg("KOIN_DEFAULT_MODULE","false")
}

dependencies {

    // Kotlin
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.collections.immutable)

    // Koin
    implementation(libs.koin.annotations)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    ksp(libs.koin.ksp.compiler)

    // Test
    testImplementation(libs.junit)
}
