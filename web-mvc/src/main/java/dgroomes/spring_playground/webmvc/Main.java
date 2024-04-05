package dgroomes.spring_playground.webmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var app = new SpringApplication(Main.class);
        app.run(args);
        log.info("Open http://[::1]:8080 in your browser to see a message. Press Ctrl-C to stop the program and server.");
    }
}

@RestController("/")
class MyController {

    @GetMapping
    String hello() {
        return "Hello from `spring-playground/web-mvc`!";
    }
}
