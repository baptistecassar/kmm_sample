import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("kotlin-android-extensions")
    id("com.squareup.sqldelight")
}

kotlin {

    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    android()

    val serializationVersion = "1.0.0-RC"
    val coroutinesVersion = "1.3.9-native-mt"
    val androidxLifecycleVersion = "2.2.0"

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")

                // SQL Delight
                implementation("com.squareup.sqldelight:runtime:${Versions.sqlDelight}")

                // KTOR
                implementation(Ktor.clientSerialization)
                implementation(Ktor.clientCore)
                implementation(Ktor.clientLogging)

                // koin
                api(Koin.test)
                api(Koin.core)

                // MOKO - MVVM
                api(Moko.mokoMvvmCore)
                api(Moko.mokoMvvmLiveData)
                api(Moko.mokoMvvmState)

                // kermit
                api(Deps.kermit)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.4.0")
                implementation(Ktor.clientAndroid)
                implementation("androidx.lifecycle:lifecycle-extensions:$androidxLifecycleVersion")
                // SQL Delight
                implementation("com.squareup.sqldelight:android-driver:${Versions.sqlDelight}")
                // MOKO - MVVM
                api(Moko.mokoMvvmLiveDataMaterial)
                api(Moko.mokoMvvmLiveDataGlide)
                api(Moko.mokoMvvmLiveDataSwipeRefresh)
                api(Moko.mokoMvvmDataBinding)
                api(Moko.mokoMvvmViewBinding)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(Test.junit)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Ktor.clientIos)
                // SQL Delight
                implementation("com.squareup.sqldelight:native-driver:${Versions.sqlDelight}")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdk = 30
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 30
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)