import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

group = "chicagocloudconference.com"
version = "1.0"

tasks {
    named<ShadowJar>("shadowJar") {
        manifest {
            attributes(mapOf("Main-Class" to "ChicagoCloudConference.Main"))
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}
