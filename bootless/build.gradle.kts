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
    implementation(libs.slf4j.simple)
    implementation(libs.spring.context)
}

application {
    mainClass.set("dgroomes.spring_playground.bootless.Main")
}
