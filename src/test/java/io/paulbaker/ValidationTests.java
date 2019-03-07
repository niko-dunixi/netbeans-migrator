package io.paulbaker;

import io.paulbaker.integration.MavenUtils;
import lombok.extern.java.Log;
import org.apache.maven.project.MavenProject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static io.paulbaker.MavenProjectBuilder.builder;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Paul Nelson Baker
 * @author WGU Student Info: pbake22 - #000412290
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2019-03
 */
@Log
public class ValidationTests {

    @Test
    public void testTheOtherThing() {
        log.info("Test was executed");
    }

    @Test
    public void testTheThing() {
        log.info("Test was executed, but will fail");
        fail("This test has failed intentionally");
    }

    public static Stream<Object[]> mavenProjectSupplier() {
        return Arrays.stream(new Object[][]{
                new Object[]{builder().artifactId("artifact-id").build(), "artifact-id"},
                new Object[]{builder().name("name").build(), "name"},
                new Object[]{builder().artifactId("artifact-id").name("name").build(), "name"}
        });
    }

    //    @Test
    @ParameterizedTest
    @MethodSource("mavenProjectSupplier")
    public void testProjectResolvesToName(MavenProject mavenProject, String expectedName) {
        String name = MavenUtils.getProjectName(mavenProject);
        assertNotNull(name);
        assertEquals(name, expectedName);
    }


}
