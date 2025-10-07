# bootless-annotationless-functional-endpoints

A Spring Web MVC application that uses the "functional endpoints" APIs. No annotations. No Spring Boot.


## Overview

This project is designed to "drill to the core" of the APIs I'm interested in. In particular, I very much would like to
explore the [*Functional Endpoints*](https://docs.spring.io/spring-framework/reference/web/webmvc-functional.html) APIs
so that I can author my route handling code something like the following:

```java
RouterFunction<ServerResponse> route = route() 
	.GET("/widgets/{id}", contentType(MediaType.TEXT_HTML), handler::getWidgetAsHtml)
	.GET("/widgets/{id}", handler::getWidgetAsJson)
	.POST("/widgets", handler::createWidget)
	.build();
```

I want to de-scope APIs and software machinery that I'm not interested in like Spring Boot's autoconfiguration.

This project builds on concepts I explore in my other subprojects like [bootless](../bootless) and [bootless-web-mvc](../bootless-web-mvc).
Study the source code and READMEs in those projects for useful background information.


## Instructions

Follow these instructions to build and run the example program.

1. Pre-requisite: Java
    * I used Java 25
2. Build the program distribution
    * ```shell
      ./gradlew installDist
      ```
3. Start the Spring program and Tomcat server
    * ```shell
      ./build/install/bootless-annotationless-functional-endpoints/bin/bootless-annotationless-functional-endpoints
      ```
    * The program will log something that looks like the following.
    * ```text
      23:18:35.829 [main] INFO dgroomes.spring_playground.bootless_annotationless_functional_endpoints.Main - Starting an embedded Tomcat server and wiring up a simple Spring web application context...
      23:18:36.310 [main] DEBUG dgroomes.spring_playground.bootless_annotationless_functional_endpoints.Main - Tomcat server started and Spring application context initialized in PT0.480576S
      23:18:36.311 [main] INFO dgroomes.spring_playground.bootless_annotationless_functional_endpoints.Main - Open http://[::1]:8080/messages in your browser to see the message. Press Ctrl-C to stop the program and server.
      ```
4. Open the browser
    * Let's see the final effect by opening the browser to <http://[::1]:8080/messages>. You should see a special
      message from the server.
5. Add your own messages
    * ```shell
      curl -X POST --header 'Content-Type: text/plain' --data 'Hello there.' http://[::1]:8080/messages
      curl -X POST --header 'Content-Type: text/plain' --data 'Hello again there.' http://[::1]:8080/messages
      curl -X POST --header 'Content-Type: text/plain' --data 'Hello one more time.' http://[::1]:8080/messages
      ```
    * Your message was added. Refresh the browser to see it.
6. Delete the first message
    * ```shell
      curl -X DELETE http://[::1]:8080/messages/1
      ```
    * The first message was deleted. Refresh the browser to see the change.
7. Let's try a query parameter
    * ```shell
      curl http://[::1]:8080/messages?limit=2
      ```
    * This will limit the messages to just the first two.
8. Stop the server
    * When you're ready, stop the demo program and server with the `Ctrl+C` key combination.


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [x] DONE Make it work *with* annotations.
* [x] DONE Flesh out the various endpoints/methods. Get multiple messages limited by a `limit` query param,
  create a message, get by ID.
* [x] DONE (it works! I need to clean it up) Make it work *without* annotations.
   * I'm not sure the annotation-ness is really feasible. But I still want to try it. 
   * By going annotation-less, I actually might want to go full non-framework and just invoke methods and constructors
     directly as needed instead of going through the application context. This style is what I called "bare bones" in
     my [kafka-playground](https://github.com/dgroomes/kafka-playground/tree/7fff26096100823f2368b8b0bcb2cf90b35b90a6/spring-barebones) repository.
* [x] DONE Replace Undertow with Tomcat or Jetty. Undertow is somewhat of an evolutionary dead-end because of the lack of
  major updates in the last few years and their stated intention to reimplement on top of Netty. I'm looking for a "small"
  HTTP server for use-cases like these. Tomcat and Jetty are even bigger than I'd like but Spring Web MVC requires a
  servlet-based HTTP server and I'm not willing to use the reactive stack.
* [ ] Use JSON. This is interesting for route handling (content negotiation) and also it's realistic.
