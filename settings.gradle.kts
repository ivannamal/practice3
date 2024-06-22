rootProject.name = "pr3_java"
dependencyResolutionManagement{
    repositories{
        mavenCentral()
    }
}

/*rootDir.listFiles().filter { it.isDirectory && !it.isHidden }.forEach{
    include(it.name)
}*/
include("app")
include("business-logic")
include("data-model")
include("java-library")
