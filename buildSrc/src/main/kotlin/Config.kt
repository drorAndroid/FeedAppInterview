import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin


const val kotlinVersion = "1.3.41"

val DependencyHandler.kotlinStdlib get() = kotlin("stdlib-jdk8", version = kotlinVersion)

object Config {

    object Versions {
        const val dagger2 = "2.16"
        const val appcompat = "1.0.0"
        const val recyclerView = "1.0.0"
        const val cardView = "1.0.0"
        const val constraintLayout = "1.1.2"

        const val rxJava = "2.2.10"
        const val rxKotlin = "2.3.0"
        const val rxAndroid = "2.1.1"
        const val coroutinesCore = "1.3.0-M2"
        const val coroutinesAndroid = "1.3.0-M2"

        // testing
        const val junit = "4.12"
        const val powerMock = "2.0.2"
        const val mockito = "2.28.2"
        const val testRunner = "1.1.0"
        const val espressoCore = "3.1.0"
    }

    object Android {
        const val minSdk = 19
        const val buildTools = "28.0.3"
        const val compileSdk = 28
        const val targetSdk = compileSdk
    }


    object Testing {
        const val junit = "junit:junit:${Versions.junit}"
        const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
        const val testRunner = "androidx.test:runner:${Versions.testRunner}"

        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

        const val powerMockCore = "org.powermock:powermock-core:${Versions.powerMock}"
        const val powerMockApi = "org.powermock:powermock-api-mockito2:${Versions.powerMock}"
        const val powerMockJunit = "org.powermock:powermock-module-junit4:${Versions.powerMock}"
        const val powerMockJunitRule =
            "org.powermock:powermock-module-junit4-rule:${Versions.powerMock}"
    }

    object Libraries {
        const val appcompatV7 = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Config.Versions.recyclerView}"
        const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val constraintLayoutSolver =
            "androidx.constraintlayout:constraintlayout-solver:${Versions.constraintLayout}"

        const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"

        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"

        const val dagger2 = "com.google.dagger:dagger:${Versions.dagger2}"
        const val dagger2Compiler = "com.google.dagger:dagger-compiler:${Versions.dagger2}"
    }
}


