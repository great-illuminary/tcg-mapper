import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    alias(additionals.plugins.android.library)
    alias(additionals.plugins.kotlin.multiplatform)
    alias(additionals.plugins.kotlin.serialization)
    alias(additionals.plugins.multiplatform.buildkonfig)
    id("jvmCompat")
    id("publication")
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }

    jvm()

    js(IR) {
        browser()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.moko.resources)
                api(libs.multiplatform.moko.resources.ext)
                api(additionals.kotlinx.coroutines)
                api(additionals.kotlinx.serialization.json)
                api(additionals.multiplatform.file.access)
                api(additionals.multiplatform.http.client)

                api(libs.yaml)
            }
        }
        val commonTest by getting {
            dependencies {
                api(kotlin("test"))
                api(additionals.kotlinx.coroutines.test)
                api(additionals.multiplatform.platform)
            }
        }
    }
}

android {
    namespace = "eu.codlab.tcgmapper"
}

buildkonfig {
    packageName = "eu.codlab.tcgmapper.buildconfig"

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "commit", rootProject.extra["commit"] as String)
    }
}
