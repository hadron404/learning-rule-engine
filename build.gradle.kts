group = "org.example"
version = "1.0-SNAPSHOT"

allprojects {

}

subprojects {
    group = "org.example"
    version = "1.0.0"

    repositories {
        mavenCentral()
        mavenLocal()
    }
}

