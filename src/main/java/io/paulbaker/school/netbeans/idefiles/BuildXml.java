package io.paulbaker.school.netbeans.idefiles;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Paul Nelson Baker
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2018-07
 * WGU Student Info: pbake22 - #000412290
 */
@Getter
@JacksonXmlRootElement(localName = "project")
public class BuildXml {

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "default", isAttribute = true)
    private String _default = "default";

    @JacksonXmlProperty(isAttribute = true)
    private String basedir = ".";

    @JacksonXmlProperty(localName = "description")
    private TextValueElement description;

    @JacksonXmlProperty(localName = "import")
    private Import anImport;

    BuildXml(String name) {
        this.name = name;
        description = new TextValueElement(String.format("Builds, tests, and runs the project %s", name));
        anImport = new Import("nbproject/build-impl.xml");
    }

    @Getter
    @AllArgsConstructor
    public static class Import {
        @JacksonXmlProperty(isAttribute = true)
        private String file;
    }
}
