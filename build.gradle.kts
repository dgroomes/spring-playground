import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    id("org.springframework.boot") version "2.3.3.RELEASE" apply false // releases: https://spring.io/projects/spring-boot#learn
}

val slf4jVersion = "1.7.30" // releases: http://www.slf4j.org/news.html
val junitJupiterVersion = "5.6.2" // releases: https://junit.org/junit5/docs/current/release-notes/index.html
val jacksonVersion = "2.10.4" // releases: https://github.com/FasterXML/jackson/wiki/Jackson-Releases

subprojects {
    apply(plugin = "java")
    apply(plugin = "application")
    repositories {
        mavenCentral()
    }

    dependencies {
        "implementation"("org.slf4j:slf4j-api:$slf4jVersion")

        "testImplementation"("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
        "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    }

    tasks {
        withType(Test::class.java) {
            useJUnitPlatform()

            testLogging {
                showStandardStreams = true
                exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            }
        }
    }
}

project(":caching") {
    apply(plugin = "io.spring.dependency-management")

    /*
    Use Spring Boot dependency management without applying the Spring Boot Gradle plugin.
    See https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/#managing-dependencies-dependency-management-plugin-using-in-isolation

    F.A.Q
    Q: Why not use the Spring Boot plugin?
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
    the<DependencyManagementExtension>().apply {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }

    ext["junit-jupiter.version"] = junitJupiterVersion

    dependencies {
        "implementation"("org.springframework.boot:spring-boot-starter")
        "implementation"("org.springframework.boot:spring-boot-starter-cache")
        /**
         * Include the 'web' and 'actuator' starters as a vehicle to inspect the cache metrics. The focal point of the
         * 'caching' sub-project is caching *not* web stuff.
         */
        "implementation"("org.springframework.boot:spring-boot-starter-web")
        "implementation"("org.springframework.boot:spring-boot-starter-actuator")

        "testImplementation"("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "junit")
        }
    }

    configure<ApplicationPluginConvention> {
        mainClassName = "dgroomes.springplayground.caching.Main"
    }
}
