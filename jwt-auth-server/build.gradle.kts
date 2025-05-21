plugins {
    java
    id("io.freefair.lombok") version "8.13.1"
    id("org.springframework.boot") version "3.4.5"
}

apply(plugin = "io.spring.dependency-management")

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
