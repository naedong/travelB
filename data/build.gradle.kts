plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "kr.tr.data"
    compileSdk = AppConfig.compildSdk

    defaultConfig {
        minSdk = AppConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField(
            "String",
            "API_KEY",
            "\"${secret.API_KEY}\""
        )

    }
    ext {
        kotlin{
            version = Version.kotlinVersion
        }
    }


    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug"){
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        buildConfig = true
    }
}
dependencies {
    implementation(project(mapOf("path" to ":domain")))

    implementation(Libraries.annotationVersion)

    // coroutine
    implementation(Libraries.coroutines.coroutineCore)
    implementation(Libraries.coroutines.coroutineAndroid)

    // paging
    implementation(Libraries.room.roomPaging)

    // inject
    //hilt
    implementation(Libraries.dagger.hiltAndroid)
    ksp(Libraries.room.roomCompiler)
    // network
    implementation(Libraries.network.retrofit)
    implementation(Libraries.network.okhttp3)
    implementation(Libraries.network.convertorGson)
    implementation(Libraries.network.okhttp3Logging)

}