plugins {
    java
    application
}

val springBootVersion = "3.2.4" // https://spring.io/projects/spring-boot#learn

repositories {
    mavenCentral()
}

dependencies {
    /*
    Import the Spring Boot Maven BOM. This is convenient because we get automatic version inference for all other Spring
    and Spring-related dependency declarations. See https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#managing-dependencies
    Gradle natively supports importing Maven BOMs. See https://docs.gradle.org/current/userguide/platforms.html#sub:bom_import

    F.A.Q

    Q: Why not use the Spring Boot Gradle plugin?
    A: Because we are comfortable using the built-in Gradle 'application' plugin.

    Q: What is the difference between the 'application' plugin and the Spring Boot plugin?
    A: Among other things the Spring Boot plugin creates an executable (a.k.a. "fat) JAR. It also defines a 'bootRun'
       Gradle task that runs the executable JAR. By contrast, the 'application' plugin does not make an executable JAR
       and instead generates a shell "start script" that can be used to run the application.

    Q: Should I use an executable JAR or a start scripts?
    A: It is mostly a matter of preference. I find that Gradle's incremental build feature works more effectively when
       using the 'application' plugin compared to using the Spring Boot plugin. I think this is because the Spring Boot
       plugin has to make a whole JAR for any change to the project whereas the 'application' plugin does not have to
       pay that cost.
    */
    implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
}

application {
    mainClass.set("dgroomes.spring_playground.web_mvc.Main")
}
