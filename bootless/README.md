# bootless

A basic Spring Framework application *without Spring Boot*.


### Overview

Why make a Spring application without Spring Boot? For educational purposes of course.


## Instructions

Follow these instructions to build and run the example program.

1. Use Java 17
2. Run the app:
    * ```shell
      ./gradlew run
      ```
    * Observe the logs. Spring logs some startup information and our own code logs custom messages. Here are snippets of
      the output.
    * ```text
      12:43:18 [main] INFO dgroomes.springplayground.bootless.Main - Wiring up a simple Spring application context
      ...
      12:43:18 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@3108bc
      ...
      12:43:22 [scheduledExecutorFactoryBean-1] INFO dgroomes.springplayground.bootless.Beans - Hello, I am a 'Runnable' that was instantiated and executed on a schedule via Spring Framework
      ```


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [ ] Learn the Bean lifecycle better and specifically learn how to shutdown the schedule executor in an idiomatic way
* [ ] Can I configure and start a Spring web server (i.e. SpringMVC) without Spring Boot? I remember looking and could not
  find a way to do this. I know that it would be rare to ever do this, but I assume it has to be possible and even
  officially supported because after all, Spring Framework still has legs without Spring Boot.


## Referenced material

* [Official docs: "Spring Framework Documentation"](https://docs.spring.io/spring-framework/docs/current/reference/html/)
* [Official docs: "Task Execution and Scheduling"](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#scheduling)

