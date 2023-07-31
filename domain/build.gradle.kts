plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    id("com.google.devtools.ksp")
}

android {
    namespace = "kr.tr.domain"
    compileSdk = AppConfig.compildSdk

    defaultConfig {
        minSdk = AppConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }
    ext {
        kotlin{
            version = Version.kotlinVersion
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

}
dependencies {

    implementation(project(mapOf("path" to ":common")))

    implementation(Libraries.compose.ui.composePaging)


    implementation(Libraries.coroutines.coroutineCore)
    implementation(Libraries.coroutines.coroutineAndroid)

    implementation(Libraries.network.retrofit)

    //hilt
    implementation(Libraries.dagger.hiltAndroid)
    ksp(Libraries.room.roomCompiler)
}