plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "kr.tr.home"
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

    implementation(project(mapOf("path" to ":common")))
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
    // kapt to ksp

    //hilt
    ksp(Libraries.dagger.hiltAndroidCompiler)
    implementation(Libraries.nav.hiltNav)
//

    androidTestImplementation(Libraries.junit.extJunit)
    androidTestImplementation(Libraries.junit.esprressoCore)
}