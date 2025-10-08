# config

Showcasing configuration features of Spring Boot and the Spring Framework like Spring profiles.


## Instructions

Follow these instructions to build and run the example program.

1. Pre-requisite: Java
   - I used Java 25
2. Build the program distribution:
   - ```shell
     ./gradlew installDist
     ```
3. Run the program:
    - ```shell
      ./build/install/config/bin/config
      ```
4. Alternatively, enable a Spring Boot profile named "day" and run the program:
    - ```shell
      SPRING_PROFILES_ACTIVE=day ./build/install/config/bin/config
      ```
    - Notice how the fortune messages have changed.
5. Try the "dynamic" profile too:
    - ```shell
      SPRING_PROFILES_ACTIVE=dynamic ./build/install/config/bin/config
      ```


## Reference

- [Spring Boot docs: Profiles](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-profiles)
- [Spring Framework docs](https://docs.spring.io/spring-framework/docs/current/reference/html/)
- [Spring Framework docs: "Resources as Dependencies"](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#resources-as-dependencies)
