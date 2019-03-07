package io.paulbaker.testutils;

import org.apache.maven.model.Model;
import org.apache.maven.project.MavenProject;

import static java.util.Objects.nonNull;

/**
 * @author Paul Nelson Baker
 * @author WGU Student Info: pbake22 - #000412290
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2019-03
 */
public class MavenProjectBuilder {

    private String groupId;
    private String artifactId;
    private String version;
    private String name;

    private MavenProjectBuilder() {
    }

    public static MavenProjectBuilder builder() {
        return new MavenProjectBuilder();
    }

    public MavenProjectBuilder groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public MavenProjectBuilder artifactId(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    public MavenProjectBuilder version(String version) {
        this.version = version;
        return this;
    }

    public MavenProjectBuilder name(String name) {
        this.name = name;
        return this;
    }

    public MavenProject build() {
        Model model = new Model();
        if (nonNull(groupId)) {
            model.setGroupId(groupId);
        }
        if (nonNull(artifactId)) {
            model.setArtifactId(artifactId);
        }
        if (nonNull(version)) {
            model.setVersion(version);
        }
        if (nonNull(name)) {
            model.setName(name);
        }
        return new MavenProject(model);
    }
}
