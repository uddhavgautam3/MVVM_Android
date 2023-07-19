pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
        flatDir {
            dirs("libs")
        }
    }
}

rootProject.name = "MVVM"
include(":app")
