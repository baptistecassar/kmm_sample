object Versions {
    const val kotlin = "1.5.21"
    const val kotlinCoroutines = "1.4.3-native-mt"
    const val ktor = "1.6.3"
    const val kotlinxSerialization = "1.1.0"
    const val koin = "3.0.2"
    const val sqlDelight = "1.5.0"
    const val kermit = "0.1.9"

    const val sqliteJdbcDriver = "3.30.1"
    const val slf4j = "1.7.30"

    const val compose = "1.0.1"
    const val wearCompose = "1.0.0-alpha03"
    const val navCompose = "2.4.0-alpha06"
    const val accompanist = "0.16.0"

    const val junit = "4.13"
    const val testRunner = "1.3.0"

    const val moko = "0.11.0"
    const val mokoTest = "0.11.0"
    const val mokoResources = "0.15.1"
}


object AndroidSdk {
    const val min = 21
    const val compile = 29
    const val target = compile
}

object Deps {
    const val kermit = "co.touchlab:kermit:${Versions.kermit}"
}

object Test {
    const val junit = "junit:junit:${Versions.junit}"
}

object Compose {
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"

    const val wearFoundation = "androidx.wear.compose:compose-foundation:${Versions.wearCompose}"
    const val wearMaterial = "androidx.wear.compose:compose-material:${Versions.wearCompose}"

    const val coilCompose = "io.coil-kt:coil-compose:1.3.1"
    const val accompanistNavigationAnimation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"

    const val composeUiTest = "androidx.compose.ui:ui-test:${Versions.compose}"
    const val composeUiTestJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
}

object Koin {
    const val test = "io.insert-koin:koin-test:${Versions.koin}"
    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val android = "io.insert-koin:koin-android:${Versions.koin}"
    const val compose = "io.insert-koin:koin-androidx-compose:3.0.1"
}

object Ktor {
    const val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
    const val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"

    const val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val clientApache = "io.ktor:ktor-client-apache:${Versions.ktor}"
    const val slf4j = "org.slf4j:slf4j-simple:${Versions.slf4j}"
    const val clientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
    const val clientCio = "io.ktor:ktor-client-cio:${Versions.ktor}"
    const val clientJs = "io.ktor:ktor-client-js:${Versions.ktor}"
}

object Serialization {
    val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
}

object SqlDelight {
    const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
    const val coroutineExtensions =
        "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
    const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"

    const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
    const val nativeDriverMacos =
        "com.squareup.sqldelight:native-driver-macosx64:${Versions.sqlDelight}"
    const val jdbcDriver = "org.xerial:sqlite-jdbc:${Versions.sqliteJdbcDriver}"
    const val sqlliteDriver = "com.squareup.sqldelight:sqlite-driver:${Versions.sqlDelight}"
}

object Moko {
    const val mokoResources = "dev.icerock.moko:resources:${Versions.mokoResources}"

    //        .defaultMPL(ios = true)
    const val mokoTest = "dev.icerock.moko:test:${Versions.mokoTest}"
    const val mokoMvvm = "dev.icerock.moko:mvvm:${Versions.moko}"
    const val mokoMvvmCore = "dev.icerock.moko:mvvm-core:${Versions.moko}"
    const val mokoMvvmLiveData = "dev.icerock.moko:mvvm-livedata:${Versions.moko}"
    const val mokoMvvmLiveDataMaterial =
        "dev.icerock.moko:mvvm-livedata-material:${Versions.moko}"
    const val mokoMvvmLiveDataGlide =
        "dev.icerock.moko:mvvm-livedata-glide:${Versions.moko}"
    const val mokoMvvmLiveDataSwipeRefresh =
        "dev.icerock.moko:mvvm-livedata-swiperefresh:${Versions.moko}"
    const val mokoMvvmDataBinding = "dev.icerock.moko:mvvm-databinding:${Versions.moko}"
    const val mokoMvvmViewBinding = "dev.icerock.moko:mvvm-viewbinding:${Versions.moko}"
    const val mokoMvvmState = "dev.icerock.moko:mvvm-state:${Versions.moko}"
    const val mokoMvvmStateDeprecated =
        "dev.icerock.moko:mvvm-state-deprecated:${Versions.moko}"
    const val mokoMvvmTest = "dev.icerock.moko:mvvm-test:${Versions.moko}"
}