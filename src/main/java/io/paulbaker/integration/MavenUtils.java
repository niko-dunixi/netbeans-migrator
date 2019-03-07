package io.paulbaker.integration;

import org.apache.maven.project.MavenProject;

/**
 * @author Paul Nelson Baker
 * @author WGU Student Info: pbake22 - #000412290
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2019-03
 */
public class MavenUtils {

    private MavenUtils() {
    }

    public static String getProjectName(MavenProject mavenProject) {
        return mavenProject.getName();
    }
}
