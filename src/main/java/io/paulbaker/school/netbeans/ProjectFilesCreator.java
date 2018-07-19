package io.paulbaker.school.netbeans;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.io.IOException;

import static com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator.Feature.WRITE_XML_DECLARATION;

/**
 * @author Paul Nelson Baker
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2018-07
 * WGU Student Info: pbake22 - #000412290
 */
@AllArgsConstructor
public class ProjectFilesCreator {

    private File destinationProjectDirectory;
    private String projectName;

    public void generate() throws IOException {
        ObjectWriter xmlMapper = new XmlMapper()
                .configure(WRITE_XML_DECLARATION, true)
                .writerWithDefaultPrettyPrinter();
        File buildXml = new File(destinationProjectDirectory, "build.xml");
        xmlMapper.writeValue(buildXml, new Project(projectName));
    }

    @Getter
    public static class Project {
        @JacksonXmlProperty(isAttribute = true)
        private String name;

        @JacksonXmlProperty(localName = "default", isAttribute = true)
        private String _default = "default";

        @JacksonXmlProperty(isAttribute = true)
        private String basedir = ".";

        private Description description;

        @JacksonXmlProperty(localName = "import")
        private Import anImport;

        Project(String name) {
            this.name = name;
            description = new Description(String.format("Builds, tests, and runs the project %s", name));
            anImport = new Import("nbproject/build-impl.xml");
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Description {
        @JacksonXmlText
        private String value;
    }

    @Getter
    @AllArgsConstructor
    public static class Import {
        @JacksonXmlProperty(isAttribute = true)
        private String file;
    }
}
