
plugins {
    java
    id("org.springframework.boot") version "3.1.4"
}


apply(plugin = "io.spring.dependency-management")


dependencies {

    implementation("org.springframework.experimental:spring-modulith-starter-api")
    implementation("org.springframework.experimental:spring-modulith-starter-test")

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }
}
