import org.gradle.internal.impldep.com.amazonaws.event.SDKProgressPublisher

plugins {
    id("java-library")
    id("maven-publish")
}

group = "com.utils"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // FixtureMonkey
    api("com.navercorp.fixturemonkey:fixture-monkey-starter:1.1.15")
    // Jqwik
    api("net.jqwik:jqwik:1.8.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
