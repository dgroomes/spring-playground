# scheduling

A basic Spring Boot application that showcases scheduling using the [`@Scheduled`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/annotation/Scheduled.html)
annotation.


## Instructions

Follow these instructions to build and run the example program.

1. Pre-requisite: Java
   - I used Java 25
2. Build the program distribution
    - ```shell
      ./gradlew installDist
      ```
3. Run the app:
    - ```shell
      ./build/install/scheduling/bin/scheduling
      ```
    - Notice how the custom "Spreading good vibes!" message is printed every 10 seconds.
    - ```text
      2024-04-05T00:31:24.570-05:00  INFO 36375 --- [           main] d.spring_playground.scheduling.Main      : Started Main in 0.368 seconds (process running for 0.529)
      2024-04-05T00:31:25.013-05:00  INFO 36375 --- [   scheduling-1] d.s.scheduling.GoodVibesService          : Spreading good vibes!
      2024-04-05T00:31:35.004-05:00  INFO 36375 --- [   scheduling-1] d.s.scheduling.GoodVibesService          : Spreading good vibes!
      2024-04-05T00:31:45.002-05:00  INFO 36375 --- [   scheduling-1] d.s.scheduling.GoodVibesService          : Spreading good vibes!
      ```
4. Stop the app.
    - Use the `Ctrl+C` key combination to stop the Gradle `run` task.


## Reference

- [Quartz Enterprise Job Scheduler: "Cron Trigger Tutorial"](http://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/crontrigger.html)
