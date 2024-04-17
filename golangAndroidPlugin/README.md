# Golang Android Plugin

Golang building plugin for Android project.

### Usage

- Add to buildscript 
  ```kotlin
  repositories {
      // ...
      maven("https://maven.kr328.app/releases")
  }
  dependencies {
      // ...
      classpath("com.github.kr328.golang:gradle-plugin:1.0.0")
  }
  ```

- Apply plugin to project
  ```kotlin
  plugins {
      id("golang-android")
  }
  ```

- Configure source sets
  ```kotlin
  golang {
      sourceSets {
          create("main") {
              fileName.set("libgolang.so")
              srcDir.set(file("src/main/go"))
              tags = listOf("with_android")
          }
      }
  }
  ```