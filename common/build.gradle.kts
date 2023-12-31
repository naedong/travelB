plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "kr.tr.commom"
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

    implementation(Libraries.gson.gson)

    implementation(Libraries.coil.coil)



    implementation(Libraries.compose.coreKtx)
    implementation(Libraries.appCompat.appCompat)
    implementation(Libraries.compose.material.materialVersion)
    implementation(Libraries.compose.material.material3window)

    implementation(Libraries.compose.material.materialAnimation)
    implementation(Libraries.nav.nav)



    implementation(Libraries.compose.ui.composeUiUtill)
    implementation(Libraries.compose.ui.constraintLayout)
    implementation(Libraries.compose.ui.composePaging)
    implementation(Libraries.compose.material.material3Compose)
    implementation(Libraries.anim.animationAndroid)
    implementation(Libraries.nav.navRun)
    testImplementation(Libraries.junit.junit)
    androidTestImplementation(Libraries.junit.extJunit)
    androidTestImplementation(Libraries.junit.esprressoCore)


}