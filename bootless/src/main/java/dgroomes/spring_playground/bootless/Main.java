package dgroomes.spring_playground.bootless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ScheduledExecutorService;

/**
 * A toy Spring application that runs some code on a schedule. This app *does not* use Spring Boot.
 */
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        log.info("Wiring up a simple Spring application context");
        Instant start = Instant.now();
        var context = new AnnotationConfigApplicationContext(Main.class.getPackageName());
        var sayHello = context.getBean("sayHello", Runnable.class);
        var taskScheduler = context.getBean(TaskScheduler.class);
        taskScheduler.scheduleWithFixedDelay(sayHello, Duration.ofSeconds(1));
        Instant end = Instant.now();
        log.debug("Wired up the application context in {}", Duration.between(start, end));

        Thread.sleep(5_000);

        log.info("Shutting down");
        context.close();
    }
}

@Configuration
class Beans {

    private static final Logger log = LoggerFactory.getLogger(Beans.class);

    @Bean
    public Runnable sayHello() {
        return () -> log.info("Hello, I am a 'Runnable' that was instantiated and executed on a schedule via Spring Framework");
    }

    @Bean
    public ScheduledExecutorFactoryBean scheduledExecutorFactoryBean() {
        return new ScheduledExecutorFactoryBean();
    }

    @Bean
    public TaskScheduler taskScheduler(ScheduledExecutorService scheduledExecutorService) {
        return new ConcurrentTaskScheduler(scheduledExecutorService);
    }
}
