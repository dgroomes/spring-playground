# config

Showcasing configuration features of Spring Boot and the Spring Framework like Spring Profiles.


## Instructions

Follow these instructions to build and run the example program.

* Use Java 17
* Run the program:
    * ```shell
      ./gradlew run
      ```
* Alternatively, enable a Spring Boot profile named "day" and run the program:
    * ```shell
      SPRING_PROFILES_ACTIVE=day ./gradlew run
      ```
    * Notice how the fortune messages have changed.
* Try the "dynamic" profile too:
    * ```shell
      SPRING_PROFILES_ACTIVE=dynamic ./gradlew run
      ```


## Reference

* [Spring Boot docs: Profiles](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-profiles)
* [Spring Framework docs](https://docs.spring.io/spring-framework/docs/current/reference/html/)
* [Spring Framework docs: "Resources as Dependencies"](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#resources-as-dependencies)
