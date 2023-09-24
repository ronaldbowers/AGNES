val jacksonVersion="2.15.2"
val junitVersion = "5.8.1"
val lombokVersion = "1.18.30"

plugins {
    `java-library` // <1>
    kotlin("plugin.lombok") version "1.7.0"
    id("io.freefair.lombok") version "5.3.0"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}


repositories {
    mavenLocal()
    mavenCentral()
}

sourceSets {
    create("integrationTest") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

val integrationTestImplementation by configurations.getting {
    extendsFrom(configurations.implementation.get())
}
val integrationTestRuntimeOnly by configurations.getting

configurations["integrationTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

dependencies {
    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")

    api("org.apache.commons:commons-math3:3.6.1") // <4>
    implementation("com.google.guava:guava:30.1.1-jre") // <5>
    implementation("org.ow2.asm:asm:9.2")
    implementation("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
    implementation("com.fasterxml.jackson.core:jackson-annotations:${jacksonVersion}")

    testCompileOnly("org.projectlombok:lombok:${lombokVersion}")
    testAnnotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    testImplementation("org.junit.jupiter:junit-jupiter:${junitVersion}") // <3>
    testImplementation("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
    testImplementation("com.fasterxml.jackson.core:jackson-annotations:${jacksonVersion}")

    integrationTestImplementation("org.junit.jupiter:junit-jupiter:${junitVersion}")
    integrationTestRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform() // <6>
}
