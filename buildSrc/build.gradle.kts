buildscript {
    repositories { jcenter() }

    dependencies {
        classpath( "org.jetbrains.kotlin:kotlin-serialization:$embeddedKotlinVersion")
    }
}

plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
}

dependencies {
    compile("com.android.tools.build:gradle:3.4.1")
}
