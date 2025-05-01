plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.androidx.navigation.safeargs)
    alias(libs.plugins.room)
    alias(libs.plugins.hilt)
}

android {
    namespace = "me.arsnotfound.mycontacts"
    compileSdk = 36

    defaultConfig {
        applicationId = "me.arsnotfound.mycontacts"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType(JavaCompile::class.java).configureEach {
    options.compilerArgs.add("-Xlint:deprecation")
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)
    implementation(libs.recyclerview)

    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)

    implementation(libs.hilt.android)
    annotationProcessor(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    testImplementation(libs.room.testing)
}