package io.paulbaker.testutils;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Paul Nelson Baker
 * @author WGU Student Info: pbake22 - #000412290
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2019-03
 */
public class StreamUtils {

    private StreamUtils() {
    }

    public static Object[] objectArray(Object... any) {
        return any;
    }

    public static Stream<Object[]> objectArrayStream(Object[]... objectArrays) {
        return Arrays.stream(objectArrays);
    }
}
