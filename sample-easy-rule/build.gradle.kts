plugins {
    id("java")
}
apply(plugin = "java")
dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    implementation("org.jeasy:easy-rules-core:4.1.0")
    implementation("org.slf4j:slf4j-nop:2.0.5")

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }
}
