plugins {
    java
    id("org.springframework.boot") version "3.1.4"
}


apply(plugin = "io.spring.dependency-management")


dependencies {

//    jpa 支持
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    mysql 支持
    implementation("com.mysql:mysql-connector-j:8.2.0")

//    springboot 测试支持
    implementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")

    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.jeasy:easy-rules-core:4.1.0")
    implementation("org.jeasy:easy-rules-jexl:4.1.0") {
        exclude("commons-logging", "commons-logging")
    }
    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }
}
