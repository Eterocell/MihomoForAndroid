pluginManagement {
    repositories {
        gradlePluginPortal {
            content {
                includeGroupByRegex("com.gradle.*")
            }
        }
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://androidx.dev/storage/compose-compiler/repository/") {
            content {
                includeGroup("androidx.compose.compiler")
            }
        }
    }
    versionCatalogs {
        create("deps") {
            val agpVersion = "7.4.2"

            library("android-gradle", "com.android.tools.build:gradle:$agpVersion")
        }
    }
}

rootProject.name = "golang-android-plugin"

include("gradle-plugin")
