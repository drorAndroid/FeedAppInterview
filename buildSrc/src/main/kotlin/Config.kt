import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin


const val kotlinVersion = "1.3.31"

val DependencyHandler.kotlinStdlib get() = kotlin("stdlib-jdk7", version = kotlinVersion)

object Config {

    object Versions {
        const val supportCommon = "28.0.0"
        const val supportConstraintLayout = "1.1.3"

        const val rxJava = "2.2.10"
        const val rxKotlin = "2.3.0"
        const val rxAndroid = "2.1.1"
        const val coroutinesCore = "1.3.0-M2"
        const val coroutinesAndroid = "1.3.0-M2"

        // testing
        const val junit = "4.12"
        const val powerMock = "2.0.2"
        const val mockito = "2.28.2"
        const val supportTestRunner = "1.0.2"
        const val espressoCore = "3.0.2"

    }

    object Android {
        const val minSdk = 19
        const val buildTools = "28.0.3"
        const val compileSdk = 28
        const val targetSdk = compileSdk
    }


    object AndroidSupport {
        const val appcompatV7 = "com.android.support:appcompat-v7:${Versions.supportCommon}"
        const val recyclerview = "com.android.support:recyclerview-v7:${Config.Versions.supportCommon}"
        const val cardview = "com.android.support:cardview-v7:${Versions.supportCommon}"
        const val constraintLayout = "com.android.support.constraint:constraint-layout:${Versions.supportConstraintLayout}"
    }

    object AndroidX {

    }

    object Testing {
        const val junit = "junit:junit:${Versions.junit}"
        const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
        const val testRunner = "com.android.support.test:runner:${Versions.supportTestRunner}"
        const val testOrchestrator = "com.android.support.test:orchestrator:${Versions.supportTestRunner}"

        const val espressoCore = "com.android.support.test.espresso:espresso-core:${Versions.espressoCore}"

        const val powerMockCore = "org.powermock:powermock-core:${Versions.powerMock}"
        const val powerMockApi = "org.powermock:powermock-api-mockito2:${Versions.powerMock}"
        const val powerMockJunit = "org.powermock:powermock-module-junit4:${Versions.powerMock}"
        const val powerMockJunitRule = "org.powermock:powermock-module-junit4-rule:${Versions.powerMock}"
    }

    object Libraries {
        const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"

        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
    }
}


