package dgroomes.spring_playground.bootless_web_mvc;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.LogManager;

/**
 * Please see the README.md for more information.
 */
public class Main {

    static final Logger log = LoggerFactory.getLogger(Main.class);

    static void main() throws LifecycleException {
        // Tomcat logs with JUL (java.util.logging). We want Tomcat to log through our preferred logging system which is
        // SLF4J (including the logging implementation 'slf4j-simple'). We can bridge JUL to SLF4J using the 'jul-to-slf4j'
        // library and the snippet below.
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();

        log.info("Starting an embedded Tomcat server and wiring up a simple Spring web application context...");

        var initStart = Instant.now();

        var tomcat = new Tomcat();

        // "Context" is a concept in Tomcat. It's some kind of "container". It's a bit abstract to me. I haven't learned
        // much about it yet. Can we do something with that second parameter "docBase"?
        var ctx = tomcat.addContext("", null);

        // It's easy to forget that Tomcat requires a "connector". Calling getConnector() creates and registers the
        // default HTTP connector on port 8080 if one hasn't been configured.
        var connector = tomcat.getConnector();
        connector.setProperty("address", "127.0.0.1");

        var appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(AppConfig.class);

        // We need to point the Tomcat machinery to the Spring machinery and vice versa. It took me a few hours to
        // figure out all the places that I needed to do this.
        var wrapper = Tomcat.addServlet(ctx, "dispatcherServlet", new DispatcherServlet(appContext));
        // Eagerly initialize the Spring context so any initialization errors surface before the first request.
        wrapper.setLoadOnStartup(1);
        ctx.addServletMapping("/", "dispatcherServlet");

        tomcat.start();

        var initEnd = Instant.now();
        var initDuration = Duration.between(initStart, initEnd);
        log.debug("Tomcat server started and Spring application context initialized in {}", initDuration);
        log.info("Open http://127.0.0.1:8080/message in your browser to see the message. Press Ctrl-C to stop the program and server.");
    }
}

@RestController
class MyController {

    @GetMapping("/message")
    public String message() {
        return "Hello, from a Spring web application that *is not* using Spring Boot. It's bootless!";
    }
}

@Configuration
@EnableWebMvc
@ComponentScan
class AppConfig {
}


