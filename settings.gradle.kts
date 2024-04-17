rootProject.name = "ClashMetaForAndroid"

includeBuild("golangAndroidPlugin")

include(":app")
include(":core")
include(":service")
include(":design")
include(":common")
include(":hideapi")

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://androidx.dev/storage/compose-compiler/repository/") {
            content {
                includeGroup("androidx.compose.compiler")
            }
        }
        maven("https://maven.kr328.app/releases") {
            content {
                includeGroupAndSubgroups("com.github.kr328")
            }
        }
    }
}