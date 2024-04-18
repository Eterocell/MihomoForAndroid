import java.net.URL

plugins {
    id("build-logic.root-project.base")
    id("build-logic.spotless")
}

val wrapper: Wrapper by tasks.named<Wrapper>("wrapper") {
    gradleVersion = "8.7"
    distributionType = Wrapper.DistributionType.ALL
    doLast {
        val sha256 = URL("$distributionUrl.sha256").openStream()
            .use { it.reader().readText().trim() }

        file("gradle/wrapper/gradle-wrapper.properties")
            .appendText("distributionSha256Sum=$sha256")
    }
}

tasks.clean {
    allprojects.forEach {
        delete(it.layout.buildDirectory)
        delete(files("${it.layout.projectDirectory.asFile.absolutePath}/.cxx"))
    }
}
