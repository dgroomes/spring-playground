# web-mvc

A "hello world" Spring Boot Web MVC demo.


## Instructions

Follow these instructions to build and run the example program.

1. Pre-requisite: Java
    * I used Java 21
2. Build the program distribution
    * ```shell
      ./gradlew installDist
      ```
3. Run the program:
    * ```shell
      ./build/install/web-mvc/bin/web-mvc
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
      :: Spring Boot ::                (v3.2.4)
      
      2024-04-05T00:32:29.210-05:00  INFO 36982 --- [           main] dgroomes.spring_playground.webmvc.Main   : Starting Main using Java 21.0.2 with PID 36982 (/Users/dave/repos/personal/spring-playground/web-mvc/build/install/web-mvc/lib/web-mvc.jar started by dave in /Users/dave/repos/personal/spring-playground/web-mvc)
      2024-04-05T00:32:29.212-05:00  INFO 36982 --- [           main] dgroomes.spring_playground.webmvc.Main   : No active profile set, falling back to 1 default profile: "default"
      2024-04-05T00:32:29.536-05:00  INFO 36982 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
      2024-04-05T00:32:29.541-05:00  INFO 36982 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
      2024-04-05T00:32:29.541-05:00  INFO 36982 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.19]
      2024-04-05T00:32:29.560-05:00  INFO 36982 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
      2024-04-05T00:32:29.561-05:00  INFO 36982 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 332 ms
      2024-04-05T00:32:29.696-05:00  INFO 36982 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
      2024-04-05T00:32:29.700-05:00  INFO 36982 --- [           main] dgroomes.spring_playground.webmvc.Main   : Started Main in 0.627 seconds (process running for 0.786)
      2024-04-05T00:32:29.701-05:00  INFO 36982 --- [           main] dgroomes.spring_playground.webmvc.Main   : Open http://[::1]:8080 in your browser to see a message. Press Ctrl-C to stop the program and server.
      ```
4. Make an HTTP request
   * Let's see the final effect by opening the browser to <http://[::1]:8080/>. You should see a special
     message from the server.


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [ ] Actually do something with MVC. I built this subproject for a runnable example MVC project so that I could attach a
  debugger and see how the initialization code works, but this subproject doesn't actually showcase many features of
  MVC. Maybe do something with content negotiation, optional query params, compression, access logs, tracing, the user
  agent or something.
