plugins {
    application
    kotlin("jvm") version "1.7.21"
    id("org.graalvm.buildtools.native") version "0.9.11"
}

group = "stingelin.coroutines"
version = "0.0.1"
application {
    mainClass.set("ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.4.5")
    implementation("io.ktor:ktor-server-core-jvm:2.2.1")
    implementation("io.ktor:ktor-server-cio-jvm:2.2.1")
}

graalvmNative {
    binaries {
        named("main") {
            fallback.set(false)
            verbose.set(true)

            buildArgs.add("--initialize-at-build-time=io.ktor,kotlin")

            buildArgs.add("-H:+InstallExitHandlers")
            buildArgs.add("-H:+ReportUnsupportedElementsAtRuntime")
            buildArgs.add("-H:+ReportExceptionStackTraces")

            imageName.set("graal-server")
        }
    }
}