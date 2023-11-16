plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
//    id("androidx.navigation.safeargs")
    id("androidx.navigation.safeargs.kotlin")
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.news"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.news"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled =true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding=true
    }
}

dependencies {

    implementation("com.android.car.ui:car-ui-lib:2.5.1")
    val lifeCycleExtensionVersion = ("1.1.1")
    val supportVersion = ("28.0.0")
    val retrofitVersion = ("2.6.0")
    val glideVersion = ("4.9.0")
    val rxJavaVersion = ("2.2.9")
    val roomVersion = ("2.6.0")
    val navVersion = ("2.7.5")
    val multidex_version = ("2.0.1")

    implementation ("androidx.multidex:multidex:$multidex_version")

    implementation ("android.arch.lifecycle:extensions:$lifeCycleExtensionVersion")

    implementation ("androidx.room:room-runtime:$roomVersion")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    kapt ("androidx.room:room-compiler:$roomVersion")
    implementation ("androidx.room:room-ktx:$roomVersion")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("androidx.room:room-rxjava2:2.6.0")

//    val coroutines_version = ("1.3.0")
//    val room_version = ("2.4.2")
//
//    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")
//    kapt ("androidx.room:room-runtime:$room_version")







    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")

    implementation ("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navVersion")

    implementation ("com.google.android.material:material:1.10.0")

    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")

    implementation ("io.reactivex.rxjava2:rxjava:2.2.9")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")

    implementation ("com.github.bumptech.glide:glide:$glideVersion")

    implementation ("androidx.preference:preference-ktx:1.2.1")

//    implementation ("com.android.support:palette-v7:$supportVersion")
//    implementation ("com.android.support:design:$supportVersion")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("android.arch.lifecycle:extensions:1.1.1")
}