plugins {

    java
    id("org.springframework.boot") version "3.1.4"
}

repositories {
    maven(url = "https://repo.spring.io/snapshot")
}

apply(plugin = "io.spring.dependency-management")



dependencies {
    ext {
        set("springModulithVersion", "1.1.0-SNAPSHOT")
        set("lombokVersion", "1.18.28")
    }
    implementation("org.projectlombok:lombok:${ext.get("lombokVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.modulith:spring-modulith-starter-core:${ext.get("springModulithVersion")}")
    implementation("org.springframework.modulith:spring-modulith-starter-test:${ext.get("springModulithVersion")}")


    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }
}
