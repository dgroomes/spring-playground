package dgroomes.springplayground.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;

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

    private static List<String> names = List.of("Damon", "Eliza");

    @Override
    public void run(String... args) throws InterruptedException {
        log.info("Hello!");
        var size = names.size();
        for (int i = 0; i < 10; i++) {
            var i2 = i % size;
            var fortune = fortuneService.getFortune(names.get(i2));
            log.info(fortune);
            Thread.sleep(1000);
        }
        System.exit(0);
    }
}
