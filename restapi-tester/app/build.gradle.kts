plugins {
    id("application")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jboss.weld.se:weld-se-shaded:5.1.2.Final")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")

    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("lab.leleonz.consoletester.App")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
