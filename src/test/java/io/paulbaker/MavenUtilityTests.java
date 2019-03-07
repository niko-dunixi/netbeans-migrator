package io.paulbaker;

import io.paulbaker.integration.MavenUtils;
import lombok.extern.java.Log;
import org.apache.maven.project.MavenProject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.paulbaker.testutils.MavenProjectBuilder.builder;
import static io.paulbaker.testutils.StreamUtils.objectArray;
import static io.paulbaker.testutils.StreamUtils.objectArrayStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Paul Nelson Baker
 * @author WGU Student Info: pbake22 - #000412290
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2019-03
 */
@Log
public class MavenUtilityTests {

    public static Stream<Object[]> mavenProjectSupplier() {
        return objectArrayStream(
                objectArray(builder().artifactId("artifact-id").build(), "artifact-id"),
                objectArray(builder().name("name").build(), "name"),
                objectArray(builder().artifactId("artifact-id").name("name").build(), "name")
        );
    }

    @ParameterizedTest
    @MethodSource("mavenProjectSupplier")
    public void testProjectResolvesToName(MavenProject mavenProject, String expectedName) {
        String name = MavenUtils.getProjectName(mavenProject);
        assertNotNull(name);
        assertEquals(name, expectedName);
    }
}
