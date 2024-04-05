# bootless-web

A Spring Web MVC application *without Spring Boot*.


## Overview

What's all the boilerplate needed to start up a Spring Web MVC application complete with a backing web server (like
embedded Tomcat)? How convoluted is it? How practical is it? I want to know.


## Instructions

Follow these instructions to build and run the example program.

1. Pre-requisite: Java
    * I used Java 21
2. Build the program distribution
    * ```shell
      ./gradlew installDist
      ```
3. Start the Spring program and Tomcat server
    * ```shell
      ./build/install/bootless-web-mvc/bin/bootless-web-mvc
      ```
    * The program will log something that looks like the following.
    * ```text
      15:17:48.729 [main] INFO dgroomes.springplayground.bootlesswebmvc.Main - Starting an embedded Tomcat server and wiring up a simple Spring web application context...
      15:17:49.201 [main] DEBUG dgroomes.springplayground.bootlesswebmvc.Main - Tomcat server started and Spring application context initialized in PT0.471009S
      15:17:49.202 [main] INFO dgroomes.springplayground.bootlesswebmvc.Main - Open http://localhost:8080/message in your browser to see the message. Press Ctrl-C to stop the program and server.
      ```
4. Open the browser
    * Let's see the final effect by opening the browser to <http://[::1]:8080/message>. You should see a special
      message from the server.
5. Stop the server
    * When you're ready, stop the demo program and server with the `Ctrl+C` key combination.


## Reverse Engineering Spring Boot's Web MVC Support

To understand how we can use the Spring Web MVC APIs directly (i.e. without Spring Boot), we can study how Spring Boot
codes to these APIs. In particular, I noticed a few places of interest:

* [`DispatcherServletAutoConfiguration`](https://github.com/spring-projects/spring-boot/blob/07a7ff473b6e97db6f00eb62f4f8beb2fb8da73b/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/web/servlet/DispatcherServletAutoConfiguration.java)
* [`ServletWebServerApplicationContext`](https://github.com/spring-projects/spring-boot/blob/07a7ff473b6e97db6f00eb62f4f8beb2fb8da73b/spring-boot-project/spring-boot/src/main/java/org/springframework/boot/web/servlet/context/ServletWebServerApplicationContext.java)
* [`TomcatServletWebServerFactory`](https://github.com/spring-projects/spring-boot/blob/07a7ff473b6e97db6f00eb62f4f8beb2fb8da73b/spring-boot-project/spring-boot/src/main/java/org/springframework/boot/web/embedded/tomcat/TomcatServletWebServerFactory.java)
* [`TomcatStarter`](https://github.com/spring-projects/spring-boot/blob/07a7ff473b6e97db6f00eb62f4f8beb2fb8da73b/spring-boot-project/spring-boot/src/main/java/org/springframework/boot/web/embedded/tomcat/TomcatStarter.java#L36)
* [`TomcatWebServer#initialize`](https://github.com/spring-projects/spring-boot/blob/07a7ff473b6e97db6f00eb62f4f8beb2fb8da73b/spring-boot-project/spring-boot/src/main/java/org/springframework/boot/web/embedded/tomcat/TomcatWebServer.java#L107)

In general, I find the _core_ Spring Framework software machinery tenable but when we expand to the "instantiation and
initialization code paths" of Spring MVC, the cost/benefit worsens and then when we expand to the "instantiation and
initialization code paths" of Spring Boot (I'm mainly talking about "autoconfiguration") I find it untenable. (To be clear
I do appreciate the immense work, innovation, documentation, community, energy, and support of all contributors. I use
Spring Boot and I benefit from it! It's too easy to be a critic). I think to preserve my own time, I have to decide to
not spend too much time learning the internals and mental models (complete with accidental complexity). But I will sample
some of the internals.

In particular, there is [a line in `ServletWebServerApplicationContext`](https://github.com/spring-projects/spring-boot/blob/07a7ff473b6e97db6f00eb62f4f8beb2fb8da73b/spring-boot-project/spring-boot/src/main/java/org/springframework/boot/web/servlet/context/ServletWebServerApplicationContext.java#L188)
which I find interesting.

```java
this.webServer = factory.getWebServer(getSelfInitializer());
```

This line creates a web server. Why doesn't it use the bean metaphor? It codes to a bespoke interface method: `ServletWebServerFactory#getWebServer`.
I know that building up the web server, like an embedded Tomcat instance, is especially complicated compared to the
average beans in a Spring application, like a `DataSource`, `Executor`, `RestTemplate`, etc. And I know that dealing
with the initialization concerns of a servlet container is complicated because of the weight of supporting so many
features of an API that's grown and changed over many years. And I know that the web server is a foundational component
of the overall application, so we're in the bowels of bootstrapping complexity which is often the oddest, arbitrary, and
unloved part of any software system. But, is there a way to stay aligned with the core building block metaphor of Spring,
which is beans? I'm not an expert here, so I'm oversimplifying by definition, but that's my perspective.


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [ ] Spring's alternative web support. I know you can use the functional (non-annotation) support, but I think it might
  be tied to the reactive stack. Is it?


## Reference

* [`dgroomes/tomcat-playground`](https://github.com/dgroomes/tomcat-playground)
  * This is another of my playground projects. It shows a working example of embedded Tomcat which I can conveniently
    use here.
