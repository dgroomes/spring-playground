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
    implementation(libs.springframework.spring.context)
    implementation(libs.springframework.spring.webmvc)
    implementation(libs.tomcat.embedded)
}

application {
    mainClass.set("dgroomes.springplayground.bootlesswebmvc.Main")
}
