package io.paulbaker;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

/**
 * @author Paul Nelson Baker
 * @author WGU Student Info: pbake22 - #000412290
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2019-03
 */
@Log
public class Validation {

    @Test
    public void testTheOtherThing() {
        log.info("Test was executed");
    }

    @Test
    public void testTheThing() {
        log.info("Test was executed, but will fail");
        throw new RuntimeException();
    }
}
