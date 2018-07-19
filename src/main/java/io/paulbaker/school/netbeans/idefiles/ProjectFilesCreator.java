package io.paulbaker.school.netbeans.idefiles;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;

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
        xmlMapper.writeValue(
                new File(destinationProjectDirectory, "build.xml"),
                new BuildXml(projectName)
        );


    }
}
