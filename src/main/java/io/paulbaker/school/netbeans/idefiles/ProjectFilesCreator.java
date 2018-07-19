package io.paulbaker.school.netbeans.idefiles;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

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
        // Main folder
        writeXML(
                new File(destinationProjectDirectory, "build.xml"),
                new BuildXml(projectName)
        );
        writeText(new File(destinationProjectDirectory, "manifest.mf"), new String[]{
                "Manifest-Version: 1.0",
                "X-COMMENT: Main-Class will be added automatically by build",
                ""
        });
        // Nested folder
        File nbprojectDirectory = new File(destinationProjectDirectory, "nbproject");
        //noinspection ResultOfMethodCallIgnored
        nbprojectDirectory.mkdirs();
        writeXML(
                new File(nbprojectDirectory, "project.xml"),
                new ProjectXml(projectName)
        );
        writeText(
                new File(nbprojectDirectory, "project.properties"),
                new String[]{
                        "annotation.processing.enabled=true",
                        "annotation.processing.enabled.in.editor=false",
                        "annotation.processing.processor.options=",
                        "annotation.processing.processors.list=",
                        "annotation.processing.run.all.processors=true",
                        "annotation.processing.source.output=${build.generated.sources.dir}/ap-source-output",
                        "build.classes.dir=${build.dir}/classes",
                        "build.classes.excludes=**/*.java,**/*.form",
                        "# This directory is removed when the project is cleaned:",
                        "build.dir=build",
                        "build.generated.dir=${build.dir}/generated",
                        "build.generated.sources.dir=${build.dir}/generated-sources",
                        "# Only compile against the classpath explicitly listed here:",
                        "build.sysclasspath=ignore",
                        "build.test.classes.dir=${build.dir}/test/classes",
                        "build.test.results.dir=${build.dir}/test/results",
                        "# Uncomment to specify the preferred debugger connection transport:",
                        "#debug.transport=dt_socket",
                        "debug.classpath=\\",
                        "    ${run.classpath}",
                        "debug.test.classpath=\\",
                        "    ${run.test.classpath}",
                        "# Files in build.classes.dir which should be excluded from distribution jar",
                        "dist.archive.excludes=",
                        "# This directory is removed when the project is cleaned:",
                        "dist.dir=dist",
                        "dist.jar=${dist.dir}/software-c482-javafx-application.jar",
                        "dist.javadoc.dir=${dist.dir}/javadoc",
                        "excludes=",
                        "includes=**",
                        "jar.compress=false",
                        "javac.classpath=",
                        "# Space-separated list of extra javac options",
                        "javac.compilerargs=",
                        "javac.deprecation=false",
                        "javac.external.vm=true",
                        "javac.processorpath=\\",
                        "    ${javac.classpath}",
                        "javac.source=1.8",
                        "javac.target=1.8",
                        "javac.test.classpath=\\",
                        "    ${javac.classpath}:\\",
                        "    ${build.classes.dir}",
                        "javac.test.processorpath=\\",
                        "    ${javac.test.classpath}",
                        "javadoc.additionalparam=",
                        "javadoc.author=false",
                        "javadoc.encoding=${source.encoding}",
                        "javadoc.noindex=false",
                        "javadoc.nonavbar=false",
                        "javadoc.notree=false",
                        "javadoc.private=false",
                        "javadoc.splitindex=true",
                        "javadoc.use=true",
                        "javadoc.version=false",
                        "javadoc.windowtitle=",
                        "main.class=",
                        "manifest.file=manifest.mf",
                        "meta.inf.dir=${src.dir}/META-INF",
                        "mkdist.disabled=false",
                        "platform.active=default_platform",
                        "run.classpath=\\",
                        "    ${javac.classpath}:\\",
                        "    ${build.classes.dir}",
                        "# Space-separated list of JVM arguments used when running the project.",
                        "# You may also define separate properties like run-sys-prop.name=value instead of -Dname=value.",
                        "# To set system properties for unit tests define test-sys-prop.name=value:",
                        "run.jvmargs=",
                        "run.test.classpath=\\",
                        "    ${javac.test.classpath}:\\",
                        "    ${build.test.classes.dir}",
                        "source.encoding=UTF-8",
                        "src.dir=src",
                        "test.src.dir=test\n"
                }
        );
        writeText(
                new File(nbprojectDirectory, "genfiles.properties"),
                new String[]{
                        "build.xml.data.CRC32=00000000",
                        "build.xml.script.CRC32=00000000",
                        "build.xml.stylesheet.CRC32=00000000@0.00.0.00",
                        "# This file is used by a NetBeans-based IDE to track changes in generated files such as build-impl.xml.",
                        "# Do not edit this file. You may delete it but then the IDE will never regenerate such files for you.",
                        "nbproject/build-impl.xml.data.CRC32=00000000",
                        "nbproject/build-impl.xml.script.CRC32=00000000",
                        "nbproject/build-impl.xml.stylesheet.CRC32=00000000@0.00.0.00"
                }
        );
        writeText(
                new File(nbprojectDirectory, "build-impl.xml"),
                new String[]{
                        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>",
                        "<project xmlns:j2seproject1=\"http://www.netbeans.org/ns/j2se-project/1\" xmlns:j2seproject3=\"http://www.netbeans.org/ns/j2se-project/3\" xmlns:jaxrpc=\"http://www.netbeans.org/ns/j2se-project/jax-rpc\" basedir=\"..\" default=\"default\" name=\"" + projectName + "\">",
                        "</project>"
                }
        );
    }

    private void writeXML(File file, Object pojo) throws IOException {
        ObjectWriter xmlMapper = new XmlMapper()
                .configure(WRITE_XML_DECLARATION, true)
                .writerWithDefaultPrettyPrinter();
        xmlMapper.writeValue(file, pojo);
    }

    private void writeText(File file, String[] lines) throws FileNotFoundException {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (String line : lines) {
                printWriter.write(line);
                printWriter.write(System.lineSeparator());
            }
        }
    }
}
