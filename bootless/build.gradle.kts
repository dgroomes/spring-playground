plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.slf4j.api)
    implementation(libs.slf4j.simple)
    implementation(libs.springframework.spring.context)
}

application {
    mainClass.set("dgroomes.springplayground.bootless.Main")
}
