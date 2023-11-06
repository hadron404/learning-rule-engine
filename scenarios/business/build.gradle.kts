
plugins {
    java
    id("org.springframework.boot") version "3.1.4"
}


apply(plugin = "io.spring.dependency-management")


dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-test")

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }
}
