plugins {
    id("cz.alenkacz.gradle.scalafmt") version "1.8.0"
    id("com.github.maiflai.scalatest") version "0.25"
    id("com.github.johnrengelman.shadow") version "5.0.0"
    kotlin("jvm") version "1.4.0"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

val BeamVersion = "2.23.0"
val Log4jVersion = "2.13.3"
val ScalaVersion = "2.13.3"

subprojects {
    apply(plugin = "cz.alenkacz.gradle.scalafmt")
    apply(plugin = "com.github.maiflai.scalatest")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "java")
    apply(plugin = "scala")

    dependencies {
        // Scala
        implementation("org.scala-lang:scala-library:$ScalaVersion")
        implementation("org.scala-lang:scala-reflect:$ScalaVersion")
        implementation("org.scala-lang:scala-compiler:$ScalaVersion")

        // Beam
        implementation("org.apache.beam:beam-sdks-java-core:$BeamVersion")
        implementation("org.apache.beam:beam-runners-google-cloud-dataflow-java:$BeamVersion")
        implementation("org.apache.beam:beam-sdks-java-io-google-cloud-platform:$BeamVersion")
        implementation("org.apache.beam:beam-sdks-java-extensions-google-cloud-platform-core:$BeamVersion")

        // Log4j2
        implementation("org.apache.logging.log4j:log4j-api:$Log4jVersion")
        implementation("org.apache.logging.log4j:log4j-core:$Log4jVersion")
        implementation("org.slf4j:slf4j-simple:1.7.30")
    }

    scalafmt {
        configFilePath = ".scalafmt.conf"
    }
}

