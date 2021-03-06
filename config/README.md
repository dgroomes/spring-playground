# config

Showcasing configuration features of Spring Boot and the Spring Framework like Spring Profiles.

## Instructions

* Use Java 11
* Run the program:
  * `./gradlew run`
* Alternatively, enable a Spring Boot profile named "day" and run the program:
  * `SPRING_PROFILES_ACTIVE=day ./gradlew run`
  * Notice how the fortune messages have changed.
* Try the "dynamic" profile too:
  * `SPRING_PROFILES_ACTIVE=dynamic ./gradlew run`

## Referenced materials

* [Spring Boot docs: Profiles](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-profiles)
* [Spring Framework docs](https://docs.spring.io/spring-framework/docs/current/reference/html/)
* [Spring Framework docs: *Resources as Dependencies*](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#resources-as-dependencies)
