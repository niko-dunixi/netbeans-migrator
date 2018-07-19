package io.paulbaker.school.netbeans;

import io.paulbaker.school.netbeans.idefiles.ProjectFilesCreator;
import io.paulbaker.school.netbeans.sourcefiles.SourceFileCopier;

import java.io.File;
import java.io.IOException;

/**
 * @author Paul Nelson Baker
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2018-07
 * WGU Student Info: pbake22 - #000412290
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ConverterArguments parsedArguments = ConverterArguments.parseArguments(args);

        File projectDirectory = new File(parsedArguments.getOutputProjectDirectory(), parsedArguments.getProjectName());

        SourceFileCopier sourceFileCopier = new SourceFileCopier(parsedArguments.getInputProjectDirectory(), projectDirectory);
        sourceFileCopier.copy();

        ProjectFilesCreator projectFilesCreator = new ProjectFilesCreator(projectDirectory, parsedArguments.getProjectName());
        projectFilesCreator.generate();
    }
}
