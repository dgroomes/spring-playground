package dgroomes.spring_playground.config_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var app = new SpringApplication(Main.class);
        app.run(args);
    }

    @Bean
    public ApplicationRunner runner(@Value("${app.greeting}") String greeting, @Value("${app.farewell}") String farewell) {
        return args -> {
            log.info("");
            log.info("**************************************************");
            log.info(greeting);
            log.info(farewell);
            log.info("**************************************************");
            log.info("");
        };
    }
}
