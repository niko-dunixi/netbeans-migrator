package io.paulbaker.integration;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;

/**
 * @author Paul Nelson Baker
 * @author WGU Student Info: pbake22 - #000412290
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2019-03
 */
@Mojo(name = "generate-ide-files")
public class NetbeansFileProducer extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        File netbeansProjectDirectory = getNetbeansProjectDirectory();
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.getTemplate("project.xml.template");
    }

    private File getNetbeansProjectDirectory() {
        File directory = new File(project.getBasedir(), "nbproject");
        if (directory.mkdir()) {
            getLog().info("Creating directory: " + directory);
        }
        return directory;
    }
}