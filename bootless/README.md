# bootless

A basic Spring Framework application *without Spring Boot*.


## Overview

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
* [x] DONE (Done in the subproject `bootless-web-mvc`) Can I configure and start a Spring web server (i.e. SpringMVC) without Spring Boot? I remember looking and could not
  find a way to do this. I know that it would be rare to ever do this, but I assume it has to be possible and even
  officially supported because after all, Spring Framework still has legs without Spring Boot.
* [x] DONE (Ok I learned how to do it but not going to commit it here. I'm not sure I'll ever care about startup time to
  that degree unless I also wanted to AOT the whole program ala Graal) What if I didn't use component scanning (is that the right word? I want spring to not scan the classpath).
  * In particular, I feel like the following logs show the slowness (of course for this miniscule program the duration
    is not a problem)
  * ```text
    19:20:43.425 [main] DEBUG dgroomes.springplayground.bootless.Main - Instantiating the application context...
    19:20:43.506 [main] DEBUG org.springframework.context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file
    ```
  * That's about half the time of the whole initialization. Is that the fixed cost of using an application context? It's
    fine if it is. Just curious. UPDATE: ok it's a bit faster (81ms vs 50ms (and remember we're in rounding error territory)). I think the remaining fixed cost is just class loading.
  * ```text
    19:28:25.686 [main] DEBUG dgroomes.springplayground.bootless.Main - Instantiating the application context...
    19:28:25.736 [main] DEBUG org.springframework.context.support.GenericApplicationContext - Refreshing org.springframework.context.support.GenericApplicationContext@370736d9
    ```


## Referenced material

* [Official docs: "Spring Framework Documentation"](https://docs.spring.io/spring-framework/docs/current/reference/html/)
* [Official docs: "Task Execution and Scheduling"](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#scheduling)

