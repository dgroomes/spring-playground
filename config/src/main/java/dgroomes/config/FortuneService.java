package dgroomes.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

/**
 * A toy example that requires a file for configuration. This class is a vehicle to show Spring's configuration
 * features with regard to files.
 */
@Service
public class FortuneService {

    private static final Logger log = LoggerFactory.getLogger(FortuneService.class);

    private final Random random = new Random();
    private final String[] fortunes;

    public FortuneService(@Qualifier("fortunesConfiguration") File config) throws IOException {
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
