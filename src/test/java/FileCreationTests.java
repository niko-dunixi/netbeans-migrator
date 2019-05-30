import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.paulbaker.integration.FileGenerator;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Paul Nelson Baker
 * @author WGU Student Info: pbake22 - #000412290
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2019-03
 */
@Log
public class FileCreationTests {

    private FileGenerator fileGenerator;

    @BeforeEach
    public void beforeEach() {
        fileGenerator = new FileGenerator();
    }

    public static Stream<String> projectNames() {
        return Arrays.stream(new String[]{
                "A name",
                "something-else-here",
                " a completely different thing. "
        });
    }

    @ParameterizedTest
    @MethodSource("projectNames")
    public void testFileIsGeneratedWithCorrectProjectName(String expectedName) throws IOException {
        String projectXml = fileGenerator.generateProjectXml(expectedName);
        System.out.println(projectXml);

        ObjectMapper mapper = new XmlMapper();
        Map<String, Object> map = mapper.readValue(projectXml, new TypeReference<Map<String, Object>>() {
        });

        String actualName = Optional.ofNullable(map)
                .map(m -> (Map<String, Object>) m.get("configuration"))
                .map(m -> (Map<String, Object>) m.get("data"))
                .map(m -> m.get("name"))
                .map(Object::toString)
                .get();

        assertEquals(expectedName, actualName);
    }
}
