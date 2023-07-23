package dgroomes.springplayground.bootlesswebmvc;

import jakarta.servlet.ServletContext;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
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
    AnnotationConfigWebApplicationContext appContext;
    static final int PORT = 8080;

    Tomcat tomcat;
    Context ctx;

    public static void main(String[] args) throws InterruptedException, LifecycleException {
        // Tomcat logs with JUL (java.util.logging). We want Tomcat to log through our preferred logging system which is
        // SLF4J (including the logging implementation 'slf4j-simple'). We can bridge JUL to SLF4J using the 'jul-to-slf4j'
        // library and the snippet below.
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();

        new Main().run();
    }

    public void run() throws InterruptedException, LifecycleException {
        log.info("Starting an embedded Tomcat server and wiring up a simple Spring web application context...");

        var initStart = Instant.now();

        initTomcatBoilerplate();

        appContext = new AnnotationConfigWebApplicationContext();

        // We need to point the Tomcat machinery to the Spring machinery and vice versa. It took me a few hours to
        // figure out all the places that I needed to do this.
        {
            var dispatcherServlet = new DispatcherServlet(appContext);
            Tomcat.addServlet(ctx, "dispatcherServlet", dispatcherServlet);
            ctx.addServletMappingDecoded("/", "dispatcherServlet");
            ServletContext servletContext = ctx.getServletContext();
            appContext.setServletContext(servletContext);
            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, appContext);
        }

        tomcat.start();
        appContext.register(AppConfig.class);
        appContext.refresh();
        appContext.start();

        var initEnd = Instant.now();
        var initDuration = Duration.between(initStart, initEnd);
        log.debug("Tomcat server started and Spring application context initialized in {}", initDuration);
        log.info("Open http://localhost:8080/message in your browser to see the message. Press Ctrl-C to stop the program and server.");
    }

    /**
     * Initialize a {@link Tomcat} instance along with the necessary minimal boilerplate. Part of the boilerplate is
     * the creation of a {@link Context} instance.
     */
    void initTomcatBoilerplate() {
        tomcat = new Tomcat();

        // This is kind of annoying but Tomcat, even in embedded mode, requires a working directory. It does stuff with
        // unpacking WAR files and JSP compilation. But we don't use that functionality here so the directory structure
        // is just empty directories. This is called "accidental complexity".
        tomcat.setBaseDir("tomcat-work-dir");

        // As with any server, we need to define a port to listen on.
        tomcat.setPort(PORT);

        // "Context" is a concept in Tomcat. It's some kind of "container". It's a bit abstract to me. I haven't learned
        // much about it yet. Can we do something with that second parameter "docBase"?
        ctx = tomcat.addContext("", null);

        // It's easy to forget that Tomcat requires a "connector". If you miss this, you might be left perplexed/stuck
        // when Tomcat isn't responding to any traffic. See https://stackoverflow.com/a/59282431
        var connector = new Connector("HTTP/1.1");
        connector.setPort(PORT);
        tomcat.getService().addConnector(connector);
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


