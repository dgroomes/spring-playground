# spring-playground

📚 Learning and experimenting with Spring <https://github.com/spring-projects/spring-framework>.

---

## Standalone subprojects

This repository illustrates different concepts, patterns and examples via standalone subprojects. Each subproject is
completely independent of the others and do not depend on the root project. This _standalone subproject constraint_
forces the subprojects to be complete and maximizes the reader's chances of successfully running, understanding, and
re-using the code.

The subprojects include:


### `caching/`

A basic Spring Boot application that showcases caching.

See the README in [caching/](caching/).


### `scheduling/`

A basic Spring Boot application that showcases scheduling using the [`@Scheduled`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/annotation/Scheduled.html) annotation.

See the README in [scheduling/](scheduling/).


### `config/`

Showcasing configuration features of Spring Boot and the Spring Framework like Spring Profiles.

See the README in [config/](config/).


### `web-mvc/`

A "hello world" Spring Boot Web MVC demo.

See the README in [web-mvc/](web-mvc/).


### `bootless/`

A basic Spring Framework application *without Spring Boot*.

See the README in [bootless/](bootless/).


### `bootless-web-mvc/`

A Spring Web MVC application *without Spring Boot*.

See the README in [bootless-web-mvc/](bootless-web-mvc/).


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [ ] Explore Spring's support for ahead-of-time (AOT) codegen. Spring's AOT support was created to support GraalVM
  native-image, but I'm interested in exploring it for its own sake. Consider creating a `bootless-aot` subproject.
