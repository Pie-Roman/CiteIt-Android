plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
}

android {
    namespace = "ru.pyroman.citeit.data.quote"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.kotlinx.corountines.core)

    implementation(project(":data:common-data"))
    implementation(project(":domain:quote-domain"))
}