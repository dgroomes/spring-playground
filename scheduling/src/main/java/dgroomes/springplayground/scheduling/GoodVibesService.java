package dgroomes.springplayground.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Toy example with a @Scheduled-annotated method. Spreading good vibes on a scheduled basis.
 */
@Service
public class GoodVibesService {

    private static final Logger log = LoggerFactory.getLogger(GoodVibesService.class);

    /**
     * Spread good vibes. On a schedule.
     * <p>
     * Note: with "trace" logging enabled, this method logs the current stack trace to illustrate what software machinery
     * within Spring and Spring's dependent libraries (like Quartz, in this case) is actually executing @Schedule-annotated methods.
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void spreadGoodVibes() {
        log.trace("spreadGoodVibes stacktrace:\n{}", Util.formatStackTrace(Thread.currentThread().getStackTrace()));
        log.info("Spreading good vibes!");
    }
}
