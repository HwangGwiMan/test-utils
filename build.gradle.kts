import org.gradle.internal.impldep.com.amazonaws.event.SDKProgressPublisher

plugins {
    id("java")
    id("maven-publish")
}

group = "com.core.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
//
//publishing {
//    publications {
//        mavenJava(MavenPublication) {
//            from components.java
//        }
//    }
//    repositories {
//        maven {
//            // 프로젝트 ID는 GitLab 프로젝트 설정 페이지에서 확인 가능합니다.
//            url "https://gitlab.com/api/v4/projects/${PROJECT_ID}/packages/maven"
//            name "GitLab"
//            credentials(HttpHeaderCredentials) {
//                name = 'Private-Token'
//                value = System.getenv("GITLAB_PRIVATE_TOKEN") // 로컬 배포용
//            }
//            authentication {
//                header(HttpHeaderAuthentication)
//            }
//        }
//    }
//}

dependencies {
    // FixtureMonkey
    implementation("com.navercorp.fixturemonkey:fixture-monkey-starter:1.1.15")
    // Jqwik
    implementation("net.jqwik:jqwik:1.8.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
