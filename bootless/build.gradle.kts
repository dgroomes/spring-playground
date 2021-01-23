plugins {
    java
    application
}

repositories {
    mavenCentral()
}

val slf4jVersion = "1.7.30" // releases: http://www.slf4j.org/news.html
val springFrameworkVersion = "5.3.3" // releases: https://spring.io/projects/spring-framework#learn

dependencies {
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
    implementation("org.springframework:spring-context:$springFrameworkVersion")
}

application {
    mainClass.set("dgroomes.springplayground.bootless.Main")
}
