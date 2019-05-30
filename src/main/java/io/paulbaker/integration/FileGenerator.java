package io.paulbaker.integration;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;

/**
 * @author Paul Nelson Baker
 * @author WGU Student Info: pbake22 - #000412290
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2019-03
 */
public class FileGenerator {

    private final VelocityEngine velocityEngine;

    public FileGenerator() {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
    }

    public String generateProjectXml(String projectName) {
        Template template = velocityEngine.getTemplate("project.xml.template");
        template.initDocument();

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("projectName", projectName);

        StringWriter writer = new StringWriter();
        template.merge(velocityContext, writer);

        return writer.toString();
    }
}
