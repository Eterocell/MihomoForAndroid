plugins {
    java
    `java-gradle-plugin`
    `maven-publish`
}

dependencies {
    compileOnly(libs.build.android)
    compileOnly(gradleApi())
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

gradlePlugin {
    plugins {
        create("golang-android") {
            id = "golang-android"
            implementationClass = "com.github.kr328.golang.GolangPlugin"
        }
    }
}