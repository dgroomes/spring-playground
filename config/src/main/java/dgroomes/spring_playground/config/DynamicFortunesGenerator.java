package dgroomes.spring_playground.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Generate a 'dynamic-fortunes.txt' file on app start up when the 'dynamic' Spring Profile is active. Delete the file
 * upon app exit.
 */
class DynamicFortunesGenerator implements ApplicationListener<ApplicationEvent> {

    private static final Logger log = LoggerFactory.getLogger(DynamicFortunesGenerator.class);
    public static final String FILE = "dynamic-fortunes.txt";

    /**
     * I think this is the best way to hook into the Spring lifecycle at the point where the "Environment" is established
     * but before beans are created. This is useful if you need to do some custom initialization work before the rest of
     * the Spring initialization work happens AND you need environmental information like the active Spring profiles and
     * properties. In the example below, we want to detect that the 'dynamic' Spring profile is active and only then execute
     * some custom initialization work (generating a file). We dont' want to waste time by doing this initialization work
     * if the 'dynamic ' profile is not active. This is use-case is a bit contrived, to be fair.
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            ConfigurableEnvironment env = ((ApplicationEnvironmentPreparedEvent) event).getEnvironment();
            Set<String> activeProfiles = Set.of(env.getActiveProfiles());
            if (activeProfiles.contains("dynamic")) {
                log.info("The 'dynamic' profile is enabled. Creating the '{}' file", FILE);
                createFile();
            }
        }
    }

    private void createFile() {
        log.info("Creating the '{}' file", FILE);
        var file = new File(FILE);
        file.deleteOnExit();
        var filePath = file.toPath();
        var fortunes = List.of(
                "Your lucky operating system is " + System.getProperty("os.name") + "\n",
                "Your lucky day is " + LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));
        for (String message : fortunes) {
            try {
                Files.writeString(filePath, message, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                String msg = String.format("Failed to write the '%s' file", FILE);
                throw new IllegalStateException(msg, e);
            }
        }
    }
}
