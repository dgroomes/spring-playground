plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.spring.bom))

    implementation(libs.slf4j.api)
    implementation(libs.slf4j.jul)
    runtimeOnly(libs.slf4j.simple)
    implementation(libs.spring.context)
    implementation(libs.spring.webmvc)
    implementation(libs.tomcat.embedded)
}

application {
    mainClass.set("dgroomes.spring_playground.bootless_annotationless_functional_endpoints.Main")
}
