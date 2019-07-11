plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}


android {
    compileSdkVersion(Config.Android.compileSdk)
    buildToolsVersion(Config.Android.buildTools)

    defaultConfig {
        applicationId = "com.glovoapp.feed"
        minSdkVersion(Config.Android.minSdk)
        targetSdkVersion(Config.Android.targetSdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFile("proguard-rules.pro")
        }
    }

}


dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.31")

    // Android Support
    implementation(Config.AndroidSupport.appcompatV7)
    implementation(Config.AndroidSupport.recyclerview)
    implementation(Config.AndroidSupport.cardview)
    implementation(Config.AndroidSupport.constraintLayout)

    implementation(Config.Libraries.coroutinesCore)
    implementation(Config.Libraries.coroutinesAndroid)

    implementation(Config.Libraries.rxJava)
    implementation(Config.Libraries.rxAndroid)
    implementation(Config.Libraries.rxKotlin)

    // Test
    testImplementation(Config.Testing.junit)

    testImplementation(Config.Testing.mockitoCore)
    androidTestImplementation(Config.Testing.mockitoCore)

    androidTestImplementation(Config.Testing.testRunner)
    androidTestImplementation(Config.Testing.testOrchestrator)

    androidTestImplementation(Config.Testing.espressoCore)

    testImplementation(Config.Testing.powerMockCore)
    testImplementation(Config.Testing.powerMockApi)
    testImplementation(Config.Testing.powerMockJunit)
    testImplementation(Config.Testing.powerMockJunitRule)

}

