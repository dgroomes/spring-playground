# bootless

A basic Spring Framework application *without Spring Boot*.


### Overview

Why make a Spring application without Spring Boot? For educational purposes of course.


## Instructions

Follow these instructions to build and run the example program.

* Use Java 17
* Run the app:
    * ```shell
      ./gradlew run
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

