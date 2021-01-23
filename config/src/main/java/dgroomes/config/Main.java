package dgroomes.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Current directory: {}", System.getProperty("user.dir"));
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public ApplicationRunner runner(
            @Value("${app.message}") String message,
            FortuneService fortuneService) {

        return args -> {
            log.info(message);

            for (int i = 0; i < 5; i++) {
                log.info(fortuneService.getFortune());
            }
        };
    }

    @Bean("fortunesConfiguration")
    public File fortunesConfiguration(@Value("${app.fortune-configuration}") String path) {
        return new File(path);
    }
}
