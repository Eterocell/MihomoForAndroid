import java.net.URL

plugins {
    id("build-logic.root-project.base")
    id("build-logic.spotless")
}

val wrapper: Wrapper by tasks.named<Wrapper>("wrapper") {
    gradleVersion = "8.10"
    distributionType = Wrapper.DistributionType.ALL
    val sha256 = URL("$distributionUrl.sha256").openStream()
        .use { it.reader().readText().trim() }
    distributionSha256Sum = sha256
}

tasks.clean {
    allprojects.forEach {
        delete(it.layout.buildDirectory)
        delete(files("${it.layout.projectDirectory.asFile.absolutePath}/.cxx"))
    }
}
