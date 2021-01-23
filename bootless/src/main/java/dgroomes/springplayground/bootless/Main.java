package dgroomes.springplayground.bootless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Main {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Wiring up a simple Spring application context");
        var context = new AnnotationConfigApplicationContext(Main.class.getPackageName());

        var sayHello = context.getBean("sayHello", Runnable.class);
        sayHello.run();
    }
}

@Configuration
class Beans {

    private static final Logger log = LoggerFactory.getLogger(Beans.class);

    @Bean
    public Runnable sayHello() {
        return () -> log.info("Hello, I am a 'Runnable' that was instantiated via Spring Framework");
    }
}
