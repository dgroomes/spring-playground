package dgroomes.springplayground.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Main implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    private FortuneService fortuneService;

    public Main(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public void run(String... args) {
        log.info("Hello!");
        for (int i = 0; i < 3; i++) {
            var fortune = fortuneService.getFortune();
            log.info(fortune);
        }
    }
}
