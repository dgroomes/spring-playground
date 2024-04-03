# scheduling

A basic Spring Boot application that showcases scheduling using the [`@Scheduled`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/annotation/Scheduled.html)
annotation.


## Instructions

Follow these instructions to build and run the example program.

1. Pre-requisite: Java
   * I used Java 21
2. Run the app:
    * ```shell
      ./gradlew run
      ```
    * Notice how the custom "Spreading good vibes!" message is printed every 10 seconds.
    * ```text
      2023-07-16T13:18:55.528-05:00  INFO 55607 --- [           main] d.springplayground.scheduling.Main       : Started Main in 0.337 seconds (process running for 0.474)
      2023-07-16T13:19:00.005-05:00  INFO 55607 --- [   scheduling-1] d.s.scheduling.GoodVibesService          : Spreading good vibes!
      2023-07-16T13:19:10.004-05:00  INFO 55607 --- [   scheduling-1] d.s.scheduling.GoodVibesService          : Spreading good vibes!
      2023-07-16T13:19:20.001-05:00  INFO 55607 --- [   scheduling-1] d.s.scheduling.GoodVibesService          : Spreading good vibes!
      ```
3. Stop the app.
    * Use the `Ctrl+C` key combination to stop the Gradle `run` task.


## Reference

* [Quartz Enterprise Job Scheduler: "Cron Trigger Tutorial"](http://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/crontrigger.html)
