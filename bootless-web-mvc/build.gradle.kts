plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.slf4j.api)
    runtimeOnly(libs.slf4j.simple)
    implementation(libs.slf4j.jul)
    implementation(libs.spring.context)
    implementation(libs.spring.webmvc)
    implementation(libs.tomcat.embedded)
}

application {
    mainClass.set("dgroomes.spring_playground.bootless_web_mvc.Main")
}
