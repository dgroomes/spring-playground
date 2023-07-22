# web-mvc

A "hello world" Spring Boot Web MVC demo.


## Instructions

Follow these instructions to build and run the example program.

1. Use Java 17
2. Run the program:
    * ```shell
      ./gradlew run
      ```
    * It should start up and log something that looks like the following.
    * ```text
      $ ./gradlew run
      
      > Task :run
      
      .   ____          _            __ _ _
      /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
      ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
      \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
      =========|_|==============|___/=/_/_/_/
      :: Spring Boot ::                (v3.1.2)
      
      2023-07-22T12:59:06.994-05:00  INFO 63751 --- [           main] dgroomes.webmvc.Main                     : Starting Main using Java 17.0.7 with PID 63751 (/Users/dave/repos/personal/spring-playground/web-mvc/build/classes/java/main started by dave in /Users/dave/repos/personal/spring-playground/web-mvc)
      2023-07-22T12:59:06.995-05:00  INFO 63751 --- [           main] dgroomes.webmvc.Main                     : No active profile set, falling back to 1 default profile: "default"
      2023-07-22T12:59:07.319-05:00  INFO 63751 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
      2023-07-22T12:59:07.323-05:00  INFO 63751 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
      2023-07-22T12:59:07.323-05:00  INFO 63751 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.11]
      2023-07-22T12:59:07.358-05:00  INFO 63751 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
      2023-07-22T12:59:07.359-05:00  INFO 63751 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 345 ms
      2023-07-22T12:59:07.485-05:00  INFO 63751 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
      2023-07-22T12:59:07.490-05:00  INFO 63751 --- [           main] dgroomes.webmvc.Main                     : Started Main in 0.628 seconds (process running for 0.773)
      2023-07-22T12:59:07.490-05:00  INFO 63751 --- [           main] dgroomes.webmvc.Main                     : Open http://[::1]:8080 in your browser to see a message. Press Ctrl-C to stop the program and server.
      ```
3. Make an HTTP request
   * Let's see the final effect by opening the browser to <http://[::1]:8080/>. You should see a special
     message from the server.


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [ ] Actually do something with MVC. I built this subproject for a runnable example MVC project so that I could attach a
  debugger and see how the initialization code works, but this subproject doesn't actually showcase many features of
  MVC. Maybe do something with content negotiation, optional query params, compression, access logs, tracing, the user
  agent or something.
