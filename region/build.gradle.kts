plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
}

android {
    namespace = "kr.tr.region"
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

    configurations {
        implementation {
            exclude(group = "org.jetbrains", module = "annotations")
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
        jvmTarget = JavaVersion.VERSION_17.toString()

    }

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
    //hilt
    ksp(Libraries.dagger.hiltAndroidCompiler)
    implementation(Libraries.nav.hiltNav)

    androidTestImplementation(Libraries.junit.extJunit)
    androidTestImplementation(Libraries.junit.esprressoCore)

    //liveData
    runtimeOnly(Libraries.livedata.liveData)
    implementation(Libraries.livedata.liveData)

    //webView
    implementation(Libraries.webView.webView)


}