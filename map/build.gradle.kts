import java.util.stream.Stream

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "kr.tr.map"
    compileSdk = AppConfig.compildSdk
    defaultConfig {
        minSdk = AppConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        externalNativeBuild {
            ndk {
                abiFilters.addAll(arrayOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64"))

            }
        }
    }
    ext {
        kotlin{
            version = Version.kotlinVersion
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }


    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17  .toString()

    }

//    kotlinOptions {
//        jvmTarget = AppConfig.jvmTarget
//    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.kotlinCompilerExtensionVersion
    }
}

dependencies {

    implementation(project(":common"))
    implementation(project(mapOf("path" to ":domain")))


    implementation(Libraries.compose.coreKtx)
    implementation(Libraries.appCompat.appCompat)
    implementation(Libraries.compose.material.materialVersion)
    implementation(Libraries.compose.material.material3window)
    implementation(Libraries.compose.material.material3Compose)

    // navigation
    implementation(Libraries.nav.nav)
    //  implementation(Libraries.nav.hiltNav)

    // UI
    implementation(Libraries.compose.ui.composeUiUtill)

    //coil
    implementation(Libraries.coil.coil)

    // view model
    implementation(Libraries.viewModel.viewModelScope)
    implementation(Libraries.compose.ui.composeRuntime)

    implementation(Libraries.compose.ui.accompanistPager)
    implementation(Libraries.compose.ui.accompanistPagerIndicators)
    implementation(Libraries.compose.ui.composeToolingPreview)
    implementation(Libraries.compose.ui.composePaging)

    testImplementation(Libraries.junit.junit)
    ksp(Libraries.room.roomCompiler)

    //hilt
    implementation(Libraries.dagger.hiltAndroid)
//    runtimeOnly(Libraries.nav.hiltNav)

    //hilt
    ksp(Libraries.dagger.hiltAndroidCompiler)
    implementation(Libraries.nav.hiltNav)

    // room
    implementation(Libraries.room.roomCompiler)
    implementation(Libraries.room.roomRuntime)

    androidTestImplementation(Libraries.junit.extJunit)
    androidTestImplementation(Libraries.junit.esprressoCore)

    //liveData
    runtimeOnly(Libraries.livedata.liveData)
    implementation(Libraries.livedata.liveData)



    implementation(Libraries.perssion.permission)

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(files("libs/libDaumMapAndroid.jar"))
}