package io.paulbaker.school.netbeans.idefiles;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author Paul Nelson Baker
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2018-07
 * WGU Student Info: pbake22 - #000412290
 */
@JacksonXmlRootElement(localName = "project")
public class ProjectXml {
    private TextValueElement type = new TextValueElement("org.netbeans.modules.java.j2seproject");
}
