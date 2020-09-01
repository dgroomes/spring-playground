package dgroomes.springplayground.caching;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Toy example with a @Cacheable method
 */
@Service
public class FortuneService {

    private final Random random = new Random();

    @Cacheable("fortune")
    public String getFortune() {
        var luckyNumber = random.nextInt(11);
        return String.format("Your lucky number is %s", luckyNumber);
    }
}
