package dgroomes.spring_playground.bootless_annotationless_functional_endpoints;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.stream.Collectors;

import static org.springframework.web.servlet.function.RouterFunctions.route;

/**
 * Please see the README.md for more information.
 */
public class Main {

    Logger log = LoggerFactory.getLogger(Main.class);
    AnnotationConfigWebApplicationContext appContext;
    int PORT = 8080;

    public static void main(String[] args) throws LifecycleException {
        // Tomcat logs with JUL (java.util.logging). We want Tomcat to log through our preferred logging system which is
        // SLF4J (including the logging implementation 'slf4j-simple'). We can bridge JUL to SLF4J using the 'jul-to-slf4j'
        // library and the snippet below.
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();

        new Main().run();
    }

    public void run() throws LifecycleException {
        log.info("Starting an embedded Tomcat server and wiring up a simple Spring web application context...");

        var initStart = Instant.now();

        appContext = new AnnotationConfigWebApplicationContext();

        // We need to point the Tomcat machinery to the Spring machinery and vice versa.
        {
            var dispatcherServlet = new DispatcherServlet(appContext);
            Tomcat tomcat = new Tomcat();
            tomcat.setBaseDir("tomcat-work-dir");
            tomcat.setPort(PORT);
            Context ctx = tomcat.addContext("", null);
            var connector = new Connector("HTTP/1.1");
            connector.setPort(PORT);
            tomcat.getService().addConnector(connector);
            Tomcat.addServlet(ctx, "dispatcherServlet", dispatcherServlet);
            ctx.addServletMappingDecoded("/", "dispatcherServlet");
            ServletContext servletContext = ctx.getServletContext();
            appContext.setServletContext(servletContext);
            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, appContext);

            tomcat.start();
        }

        appContext.register(AppConfig.class);
        appContext.refresh();
        appContext.start();

        var initEnd = Instant.now();
        var initDuration = Duration.between(initStart, initEnd);
        log.debug("Tomcat server started and Spring application context initialized in {}", initDuration);
        log.info("Open http://[::1]:8080/messages in your browser to see the message. Press Ctrl-C to stop the program and server.");
    }
}

record Message(int id, String content) {}

/**
 * An in-memory collection of messages plus handler methods that implement a CRUD API. The handler methods implement the
 * {@link RouterFunction} functional interface, which is a key interface of Spring's "functional endpoints" web
 * programming model.
 * <p>
 * Note: this is implemented quite verbosely. I've decided not to push repeated code into helper methods.
 */
class MessageSystem {

    private static final Logger log = LoggerFactory.getLogger(MessageSystem.class);

    int nextId = 1;

    Map<Integer, Message> messages = new HashMap<>();

    {
        messages.put(nextId++, new Message(1, "Hello! Make sure to read the README and study the source code."));
    }

    ServerResponse createMessage(ServerRequest request) {
        String content;
        try {
            content = request.body(String.class);
        } catch (ServletException | IOException e) {
            log.error("Failed to read the request body", e);
            return ServerResponse.badRequest().body("Failed to read the request body");
        }

        int id = nextId++;
        var message = new Message(id, content);
        messages.put(id, message);
        var msg = "Message created (ID: %d)".formatted(id);
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(msg);
    }

    ServerResponse getMessage(ServerRequest request) {
        String idStr = request.pathVariable("id");
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            var msg = "The 'id' path parameter must be an integer but was: '%s'".formatted(idStr);
            return ServerResponse.badRequest().body(msg);
        }

        Message message = messages.get(id);
        if (message == null) {
            return ServerResponse.notFound().build();
        }

        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(message.content());
    }

    ServerResponse getSomeMessages(ServerRequest request) {
        Optional<String> limitOpt = request.param("limit");
        if (limitOpt.isEmpty()) {
            return ServerResponse.badRequest().body("The 'limit' query parameter is required");
        }

        String limitStr = limitOpt.get();
        int limit;
        try {
            limit = Integer.parseInt(limitStr);
        } catch (NumberFormatException e) {
            var msg = "The 'limit' query parameter must be an integer but was: '%s'".formatted(limitStr);
            return ServerResponse.badRequest().body(msg);
        }

        var msg = messages.values().stream().limit(limit).map(Message::content).collect(Collectors.joining(System.lineSeparator()));
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(msg);
    }

    ServerResponse getAllMessages(ServerRequest request) {
        var msg = messages.values().stream().map(Message::content).collect(Collectors.joining(System.lineSeparator()));
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(msg);
    }

    ServerResponse updateMessage(ServerRequest request) {
        String idStr = request.pathVariable("id");
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            var msg = "The 'id' path parameter must be an integer but was: '%s'".formatted(idStr);
            return ServerResponse.badRequest().body(msg);
        }

        String content;
        try {
            content = request.body(String.class);
        } catch (ServletException | IOException e) {
            log.error("Failed to read the request body", e);
            return ServerResponse.badRequest().body("Failed to read the request body");
        }

        var message = messages.get(id);
        if (message == null) {
            return ServerResponse.notFound().build();
        }

        messages.put(id, new Message(id, content));
        var msg = "Message updated (ID: %d)".formatted(id);
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(msg);
    }

    ServerResponse deleteMessage(ServerRequest request) {
        var idStr = request.pathVariable("id");
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            var msg = "The 'id' path parameter must be an integer but was: '%s'".formatted(idStr);
            return ServerResponse.badRequest().body(msg);
        }

        if (messages.remove(id) == null) {
            return ServerResponse.notFound().build();
        }

        var msg = "Message deleted (ID: %d)".formatted(id);
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(msg);
    }
}

@Configuration
@EnableWebMvc
@ComponentScan
class AppConfig {

    MessageSystem messageSystem = new MessageSystem();

    @Bean
    public RouterFunction<?> routes() {
        /*
        Let's express a CRUD (Create, Read, Update, Delete) API for messages.

        Every route starts with "/messages" and is described by an appropriate HTTP method. GET is for reading, DELETE
        is for deleting, etc. Routes that are scoped to a specific message have a path variable '/{id}' for their
        message ID. There are three routes for "Read" functionality: one for reading a specific message by its
        ID, one for reading all messages, and one for reading some messages. The "read some" and "read all" routes
        demonstrate that the order of route declarations matters. If the "read all" router were declared before the
        "read some" route, then the "read some" route would never be matched. An incoming "/messages?limit=3" request
        would be matched by the "read all" route. The matching algorithm stops at the first matching route. This is an
        important concept.

        I find it interesting that the notion of a route is subjective. We could have expressed just one "GET /messages"
        route for the "read some" and "read all" functionality and keyed off of the presence of the 'limit' query
        parameter in the handler method. We could go even farther and include the "create" action in this same handler
        and also key off of the HTTP method. Interesting stuff.
        */
        return route()
                // Create
                .POST("/messages", messageSystem::createMessage)

                // Read (one)
                .GET("/messages/{id}", messageSystem::getMessage)

                // Read (some)
                .GET("/messages",
                        request -> request.param("limit").isPresent(),
                        messageSystem::getSomeMessages)

                // Read (all)
                .GET("/messages", messageSystem::getAllMessages)

                // Update
                .PUT("/messages/{id}", messageSystem::updateMessage)

                // Delete
                .DELETE("/messages/{id}", messageSystem::deleteMessage)
                .build();
    }
}
