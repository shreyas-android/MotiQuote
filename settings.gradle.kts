pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "MotiQuote"
include(":app")
includeBuild("../AvengAdModule") {
    dependencySubstitution {
        substitute(module("sdk_V1:avenger-ad")).using(project(":avengerad"))
        substitute(module("sdk_V1:ui")).using(project(":ui"))
    }
}

