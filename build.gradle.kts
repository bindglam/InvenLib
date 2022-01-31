plugins {
    kotlin("jvm") version "1.5.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.github.octglam"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("com.destroystokyo.paper:paper-api:1.12.2-R0.1-SNAPSHOT")
}

tasks {
    shadowJar{
        archiveClassifier.set("")
        archiveBaseName.set("InvenLib")
        archiveVersion.set("")
    }
}