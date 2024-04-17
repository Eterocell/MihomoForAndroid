import java.util.*

subprojects {
    group = "com.github.kr328.golang"
    version = "1.0.4"

    afterEvaluate {
        extensions.findByType(PublishingExtension::class)?.apply {
            val sourcesJar = tasks.register("sourcesJar", type = Jar::class) {
                archiveClassifier.set("sources")

                from((project.extensions.getByName("sourceSets") as SourceSetContainer)["main"].allSource)
            }

            publications {
                create("release", type = MavenPublication::class) {
                    pom {
                        name.set("kaidl")
                        description.set("Golang building plugin for Android project.")
                        url.set("https://github.com/Kr328/golang-android-plugin")
                        licenses {
                            license {
                                name.set("MIT License")
                                url.set("http://www.opensource.org/licenses/mit-license.php")
                            }
                        }
                        developers {
                            developer {
                                id.set("kr328")
                                name.set("Kr328")
                                email.set("kr328app@outlook.com")
                            }
                        }
                    }

                    from(components["java"])

                    artifact(sourcesJar)

                    groupId = project.group.toString()
                    artifactId = project.name
                    version = project.version.toString()
                }
            }

            repositories {
                val publishFile = rootProject.file("publish.properties")
                if (publishFile.exists()) {
                    val publish = Properties().apply { publishFile.inputStream().use(this::load) }

                    maven {
                        url = uri(publish.getProperty("publish.url")!!)

                        credentials {
                            username = publish.getProperty("publish.user")!!
                            password = publish.getProperty("publish.password")!!
                        }
                    }
                }
            }
        }
    }
}