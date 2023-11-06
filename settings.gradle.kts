pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}

rootProject.name = "learning-rule-engine"
include("sample-easy-rule")
include("generic-business-rules-service")
include("self-study-rule-engine")
include("scenarios:loyalty-program")
