package dgroomes.spring_playground.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

/**
 * Get a random "fortune message".
 *
 * This class extracts messages from some "fortunes configuration" file. This is a toy example to help show Spring's
 * configuration features with regard to files and Spring Profiles. See the @Bean-annotated methods in
 * dgroomes.config.Main to glean more information.
 */
public class FortuneService {

    private static final Logger log = LoggerFactory.getLogger(FortuneService.class);

    private final Random random = new Random();
    private final String[] fortunes;

    /**
     * @param config the fortunes configuration from which to extract messages
     * @throws IOException if the configuration file can't be read
     */
    public FortuneService(File config) throws IOException {
        log.info("Configuration file: {}", config);
        String configContent = Files.readString(config.toPath());
        log.info("Configuration content:\n{}", configContent);
        fortunes = configContent.split("\n");
        log.info("Configured with {} fortunes", fortunes.length);
    }

    /**
     * Get a fortune.
     */
    public String getFortune() {
        var idx = random.nextInt(fortunes.length);
        return fortunes[idx];
    }
}
