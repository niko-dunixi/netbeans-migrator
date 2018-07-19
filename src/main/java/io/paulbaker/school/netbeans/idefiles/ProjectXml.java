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
public class ProjectXml {
    @JacksonXmlProperty
    private TextValueElement type = new TextValueElement("org.netbeans.modules.java.j2seproject");
    @JacksonXmlProperty
    private Configuration configuration;

    ProjectXml(String projectName) {
        configuration = new Configuration(projectName);
    }

    @Getter
    private static class Configuration {
        @JacksonXmlProperty
        private Data data;

        Configuration(String projectName) {
            data = new Data(projectName);
        }
    }

    @Getter
    private static class Data {
        @JacksonXmlProperty(isAttribute = true)
        private String xmlns = "http://www.netbeans.org/ns/j2se-project/3";
        private TextValueElement name;
        @JacksonXmlProperty(localName = "source-roots")
        private SourceRoots sourceRoots = new SourceRoots("src.dir");
        @JacksonXmlProperty(localName = "test-roots")
        private SourceRoots testRoots = new SourceRoots("test.src.dir");

        Data(String projectName) {
            name = new TextValueElement(projectName);
        }
    }

    @Getter
    private static class SourceRoots {
        @JacksonXmlProperty
        private Root root;

        SourceRoots(String rootId) {
            root = new Root(rootId);
        }
    }

    @Getter
    @AllArgsConstructor
    private static class Root {
        @JacksonXmlProperty(isAttribute = true)
        private String id;
    }
}
