plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.register("checkFile") {
    description = "Checks if the specified file exists."
    group = "Verification"

    val filePath = "$rootDir/config.properties"

    doLast {
        val file = file(filePath)
        if (file.exists()) {
            println("File exists: $filePath")
        } else {
            println("File does not exist: $filePath")
        }
    }
}

tasks.register<Jar>("Jar") {
    group = "Build"
    description = "Assembles a JAR including all dependencies."

    archiveBaseName.set("my-multi-module-project")
    archiveClassifier.set("all")

    from(subprojects.flatMap { it.sourceSets.main.get().output })

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })

    with(tasks.jar.get() as CopySpec)
}