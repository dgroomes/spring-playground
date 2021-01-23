package dgroomes.springplayground.bootless;

import org.slf4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Main {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Main.class.getPackageName());

        var sayHello = context.getBean("sayHello", Runnable.class);
        sayHello.run();
    }

    @Bean
    public Runnable sayHello() {
        return () -> log.info("Hello, I am a 'Runnable' that was instantiated via Spring Framework");
    }
}
