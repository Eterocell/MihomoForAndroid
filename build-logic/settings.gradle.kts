rootProject.name = "build-logic"

pluginManagement {
    repositories {
        gradlePluginPortal {
            content {
                includeGroupByRegex("com.gradle.*")
                includeGroupByRegex("org.gradle.*")
                includeGroupByRegex("org.jetbrains.kotlin.*")
                includeGroupByRegex("com.diffplug.spotless.*")
            }
        }
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal {
            content {
                includeGroupByRegex("com.diffplug.spotless.*")
            }
        }
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

buildCache {
    local {
        removeUnusedEntriesAfterDays = 1
    }
}

include(":convention")
