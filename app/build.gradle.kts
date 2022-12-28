plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

val composeVersion: String by rootProject.extra

android {
    namespace  = "com.eschool.android"
    compileSdk = 33

    defaultConfig {
        minSdk    = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        applicationId = "com.eschool.android"

        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    kapt {
        javacOptions {
            option("-Adagger.fastInit=ENABLED")
            option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
        }
    }
}

val composeBomVersion: String by rootProject.extra
val composeActivityVersion: String by rootProject.extra
val composeConstraintLayoutVersion: String by rootProject.extra
val composeNavVersion: String by rootProject.extra
val shimmerVersion: String by rootProject.extra

val coreKtxVersion: String by rootProject.extra
val lifecycleRuntimeKtxVersion: String by rootProject.extra

val gsonVersion: String by rootProject.extra
val retrofitVersion: String by rootProject.extra
val coilVersion: String by rootProject.extra

val daggerHiltVersion: String by rootProject.extra

val commonsCodecVersion: String by rootProject.extra
val commonsLangVersion: String by rootProject.extra

dependencies {
    // UI
    implementation(platform("androidx.compose:compose-bom:${composeBomVersion}"))
    implementation("androidx.activity:activity-compose:${composeActivityVersion}")
    implementation("androidx.constraintlayout:constraintlayout-compose:${composeConstraintLayoutVersion}")
    implementation("com.valentinilk.shimmer:compose-shimmer:${shimmerVersion}")

    // Compose BOM dependencies
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material:material-icons-extended")

    // Coroutines, async, etc.
    implementation("androidx.core:core-ktx:${coreKtxVersion}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${lifecycleRuntimeKtxVersion}")
    implementation("androidx.navigation:navigation-compose:${composeNavVersion}")

    // HTTP
    implementation("com.google.code.gson:gson:${gsonVersion}")
    implementation("com.squareup.retrofit2:retrofit:${retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-gson:${retrofitVersion}")
    implementation("io.coil-kt:coil-compose:${coilVersion}")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:${daggerHiltVersion}")
    kapt("com.google.dagger:hilt-compiler:${daggerHiltVersion}")

    // Hash utilities
    implementation("commons-codec:commons-codec:${commonsCodecVersion}")
    implementation("org.apache.commons:commons-lang3:${commonsLangVersion}")

    // Previews
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}