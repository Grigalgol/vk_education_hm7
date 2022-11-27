plugins {
    id("java")
}

group = "ru.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation(project(":jooq_generated"))
    implementation("com.google.inject:guice:5.1.0")
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.google.inject.extensions:guice-servlet:5.1.0")
    implementation("org.eclipse.jetty:jetty-server:9.4.33.v20201020")
    implementation("org.eclipse.jetty:jetty-servlet:9.4.33.v20201020")
    implementation("org.eclipse.jetty:jetty-client:9.4.33.v20201020")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}