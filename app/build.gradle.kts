plugins {
    id("com.google.devtools.ksp")
    id("maven-publish")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "kr.tr.travelbproject"
    compileSdk = AppConfig.compildSdk

    defaultConfig {
        applicationId = "kr.tr.travelbproject"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        compileSdkPreview = "UpsideDownCake"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            "String",
            "API_KEY",
            "\"${secret.API_KEY}\""
        )

        buildConfigField(
            "String",
            "URL_BASE",
            "\"${WebConfig.WebBaseUrl}\""
        )


        buildConfigField(
            "String",
            "URL_SUbWAY_BASE",
            "\"${WebConfig.WebSubWayBaseUrl}\""
        )



    }


    configurations {
        implementation {
            exclude(group = "org.jetbrains", module = "annotations")
        }
        all {
            exclude(group = "org.gradle.internal.impldep.org.bouncycastle")
        }
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
            isDebuggable = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()

    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.kotlinCompilerExtensionVersion
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(":common"))
    implementation(project(":home"))
    implementation(project(":data"))
    implementation(project(":map"))
    implementation(project(":region"))
    implementation(project(":plan"))

    // annotation
    // implementation(Libraries.annotation.jetbrainAnnotation)


    implementation(Libraries.compose.ui.neumorphism)

    implementation(Libraries.nav.nav)

    //moudle
    implementation(Libraries.compose.material.materialCompose)
    implementation(Libraries.compose.material.materialVersion)
    implementation(Libraries.compose.material.material3)
    implementation(Libraries.compose.material.material3Compose)
    implementation(Libraries.compose.material.material3window)
    implementation(Libraries.compose.ui.coposeUi)
    implementation(Libraries.compose.ui.composeGraphics)
    implementation(Libraries.compose.ui.composeToolingPreview)



    implementation(Libraries.compose.coreKtx)
    implementation(platform(Libraries.jetbrains.kotlinBom))
    implementation(Libraries.lifeCycleKtx)
    implementation(Libraries.compose.activityCompose)
    implementation(platform(Libraries.compose.ui.composeBom))

    testImplementation(Libraries.junit.junit)
    androidTestImplementation(Libraries.junit.extJunit)
    androidTestImplementation(Libraries.junit.esprressoCore)
    androidTestImplementation(platform(Libraries.compose.ui.composeBom))
    androidTestImplementation(Libraries.junit.testJunit)
    debugImplementation(Libraries.compose.ui.composeTooling)
    debugImplementation(Libraries.compose.ui.testManifest)

    //network
    implementation(Libraries.network.retrofit)
    implementation(Libraries.network.okhttp3)
    implementation(Libraries.network.okhttp3Logging)
    implementation(Libraries.network.convertorGson)

    //hilt
    implementation(Libraries.dagger.hiltAndroid)

    // kapt to ksp
    ksp(Libraries.room.roomCompiler)
    ksp(Libraries.dagger.hiltAndroidCompiler)
    implementation(Libraries.nav.hiltNav)

    //liveData
    runtimeOnly(Libraries.livedata.liveData)
    implementation(Libraries.livedata.liveData)



    //splash
    implementation(Libraries.splash.splashScreen)



}