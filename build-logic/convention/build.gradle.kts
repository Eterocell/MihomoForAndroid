plugins {
    `kotlin-dsl`
}

dependencies {
    // DSL
    compileOnly(embeddedKotlin("gradle-plugin"))
    implementation(libs.gradle.plugin.android)

    implementation(libs.kotlin.stdlib)
    implementation(libs.gradle.plugin.spotless)
    implementation(libs.gradle.plugin.kotlin)
}
