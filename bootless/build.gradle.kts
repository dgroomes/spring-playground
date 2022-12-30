plugins {
    java
    application
}

repositories {
    mavenCentral()
}

val slf4jVersion = "2.0.6" // releases: http://www.slf4j.org/news.html
val springFrameworkVersion = "6.0.3" // releases: https://spring.io/projects/spring-framework#learn

dependencies {
    implementation(libs.slf4j.api)
    implementation(libs.slf4j.simple)
    implementation(libs.springframework.spring.context)
}

application {
    mainClass.set("dgroomes.springplayground.bootless.Main")
}
