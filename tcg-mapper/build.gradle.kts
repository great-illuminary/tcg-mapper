import com.codingfeline.buildkonfig.compiler.FieldSpec

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(dolbyio.plugins.android.library)
    alias(dolbyio.plugins.kotlin.multiplatform)
    alias(dolbyio.plugins.kotlin.serialization)
    alias(dolbyio.plugins.multiplatform.moko.resources.generator)
    alias(dolbyio.plugins.multiplatform.buildkonfig)
    id("jvmCompat")
    id("publication")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

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
                api(dolbyio.multiplatform.moko.resources.ext)
                api(dolbyio.kotlinx.coroutines)
                api(dolbyio.kotlinx.serialization.json)
                api(dolbyio.multiplatform.file.access)
                api(dolbyio.multiplatform.http.client)

                api("net.mamoe.yamlkt:yamlkt:0.13.0")
            }
        }
        val commonTest by getting {
            dependencies {
                api(kotlin("test"))
                api(dolbyio.kotlinx.coroutines.test)
                api(dolbyio.multiplatform.platform)
            }
        }

        val androidMain by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val jvmMain by getting
        val jsMain by getting

        listOf(
            androidMain,
            iosX64Main,
            iosArm64Main,
            iosSimulatorArm64Main,
            jvmMain,
            jsMain
        ).forEach { it.dependsOn(commonMain) }
    }
}

android {
    namespace = "eu.codlab.tcgmapper"
}

multiplatformResources {
    multiplatformResourcesPackage = "eu.codlab.tcgmapper.resources"
    multiplatformResourcesClassName = "Resources"
    multiplatformResourcesVisibility = dev.icerock.gradle.MRVisibility.Public
}

buildkonfig {
    packageName = "eu.codlab.tcgmapper.buildconfig"

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "commit", rootProject.extra["commit"] as String)
    }
}
