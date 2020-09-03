# caching

A basic Spring application that showcases caching.

---

Spring Boot (<https://spring.io/projects/spring-boot>) has a significant amount of software machinery around caching ([relevant
section in docs](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-caching)).
This project aims to de-mystify and illuminate it. Let's learn something!

### Instructions

* Use Java 14
* Source the `commands.sh` file. See [`commands.sh`](#commandssh)
* Build and run the program with `build && run`

### `commands.sh`

Source the `commands.sh` file using `source commands.sh` which will load your shell with useful commands. Commands
include:

  * `build` build (without tests)
  * `run` run the app

### Commentary

Do you even know if caching is turned on in your app? How do you prove it? I hope Spring Boot makes this easy.
PROBLEM. I can't get the cache metrics to show up in "/actuator/metrics". Why? I think I found the two sections in the
Spring Boot documentation that should tell me how to get up and running with basic caching plus basic metrics. They are:

* <https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-caching>
* <https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-metrics-cache>
* Bonus. Spring *Framework* documentation about caching: <https://docs.spring.io/spring/docs/5.2.8.RELEASE/spring-framework-reference/integration.html#cache>
There is a note that I think hints at what my problem:

> Only caches that are configured on startup are bound to the registry.

I think I need to "cause a cache to be configured on startup". How do I do that? The two aforementioned documentation
sections do not mention how to configure a cache on startup.

The application prints the stack trace from inside a `@Cacheable`-annotated method to illustrate the layers of Spring
and Spring Boot machinery between your application code and the `java` command used to run your application. In
particular, there are layers of generated classes, layers of method calls from classes in the `org.springframework.aop`
package (AOP stands for Aspect-Oriented Programming), and layers of method calls from classes in the `org.springframework.cache.interceptor`
package. It looks like this:

![stack trace](screenshots/stack-trace.png)
