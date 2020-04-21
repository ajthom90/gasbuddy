import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform

plugins {
    kotlin("multiplatform") version "1.3.70"
    kotlin("plugin.serialization") version "1.3.70"
}

group = "com.andrewthom.gas"
version = "1.0-SNAPSHOT"

val serializationVersion = "0.20.0"
val nativeEntryPoint = "com.andrewthom.gasbuddy.main"

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */

    jvm()
    macosX64 {
        binaries {
            executable {
                entryPoint = nativeEntryPoint
            }
        }
    }
	linuxX64 {
		binaries {
			executable {
				entryPoint = nativeEntryPoint
			}
		}
	}

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serializationVersion")
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.5")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

		val nativeMain by creating {
			dependsOn(commonMain)
			dependencies {
				implementation(kotlin("stdlib"))
				implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serializationVersion")
				implementation("io.ktor:ktor-client-curl:1.3.2")
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.5")
			}
		}

        jvm().compilations["main"].defaultSourceSet {
            dependsOn(commonMain)
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")
                implementation("com.konghq:unirest-java:3.7.02")
				implementation("io.ktor:ktor-client-apache:1.3.2")
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
            }
        }

        macosX64().compilations["main"].defaultSourceSet {
            dependsOn(nativeMain)
        }

		linuxX64().compilations["main"].defaultSourceSet {
			dependsOn(nativeMain)
		}
    }
}

tasks.forEach { task ->
	if (DefaultNativePlatform.getCurrentOperatingSystem().isMacOsX) {
		if (task.name.contains("linux", ignoreCase = true)) {
			task.enabled = false
		}
	}
}
