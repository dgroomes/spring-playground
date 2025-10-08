# config-location

Change the location that Spring Boot uses to find external configuration files.


## Overview

See <https://docs.spring.io/spring-boot/how-to/properties-and-configuration.html#howto.properties-and-configuration.external-properties-location>

> By default, properties from different sources are added to the Spring Environment in a defined order (see “Externalized
> Configuration” in the ‘Spring Boot features’ section for the exact order).
>
> You can also provide the following System properties (or environment variables) to change the behavior:
> 
> * `spring.config.name` (`SPRING_CONFIG_NAME`): Defaults to `application` as the root of the file name.
> * `spring.config.location` (`SPRING_CONFIG_LOCATION`): The file to load (such as a classpath resource or a URL). A
>   separate `Environment` property source is set up for this document and it can be overridden by system properties,
>   environment variables, or the command line.


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
      ./build/install/config-location/bin/config-location
      ```
    - It should print something like the following (some boilerplate omitted).
    - ```text
      **************************************************
      Hello from the 'application-default.yml' file in classpath resources
      Goodbye from the 'application-default.yml' file in classpath resources
      **************************************************
      ```
4. Alternatively, tell the application to use an alternative location to find the config data
    - ```shell
      ./build/install/config-location/bin/config-location --spring.config.additional-location=file:alternate-config/
      ```
    - Notice how the message has changed. We're not getting "Goodbye" from the classpath resources config file but
      instead a "See you later" from the config file in the `alternate-config/` directory.
    - ```text
      **************************************************
      Hello from the 'application-default.yml' file in classpath resources
      See you later, from the 'application-default.yml' file in the 'alternate-config/' directory
      **************************************************
      ```


## Reference

* [Spring Boot docs: *Externalized Configuration*](https://docs.spring.io/spring-boot/reference/features/external-config.html)
