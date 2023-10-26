rootProject.name = "learning-rule-engine"
include("sample-easy-rule")
include("generic-business-rules-service")


pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}
include("self-study-rule-engine")
