package io.paulbaker.school.netbeans.idefiles;

import lombok.AllArgsConstructor;

import java.io.*;
import java.util.Scanner;

/**
 * @author Paul Nelson Baker
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2018-07
 * WGU Student Info: pbake22 - #000412290
 */
@AllArgsConstructor
public class ProjectFilesCreator {

    private static final String PROJECT_NAME_PLACE_HOLDER = "REPLACE_ME_WITH_PROJECT_NAME";

    private File destinationProjectDirectory;
    private String projectName;

    public void generate() throws IOException {
        // Main folder
        writeResource("netbeansprojectfiles/build.xml", new File(destinationProjectDirectory, "build.xml"));
        writeText(new String[]{
                "Manifest-Version: 1.0",
                "X-COMMENT: Main-Class will be added automatically by build",
                ""
        }, new File(destinationProjectDirectory, "manifest.mf"));
        // Nested folder
        File nbprojectDirectory = new File(destinationProjectDirectory, "nbproject");
        //noinspection ResultOfMethodCallIgnored
        nbprojectDirectory.mkdirs();
        writeResource("netbeansprojectfiles/project.xml", new File(nbprojectDirectory, "project.xml"));
        writeResource("netbeansprojectfiles/project.properties", new File(nbprojectDirectory, "project.properties"));
        writeResource("netbeansprojectfiles/genfiles.properties", new File(nbprojectDirectory, "genfiles.properties"));
        writeResource("netbeansprojectfiles/build-impl.xml", new File(nbprojectDirectory, "build-impl.xml"));
    }

    private void writeResource(String resourceName, File outputFile) throws IOException {
        String resultText;
        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
             Scanner scanner = new Scanner(resourceAsStream)) {
            String fileText = scanner.useDelimiter("\\A").next();
            resultText = fileText.replaceAll(PROJECT_NAME_PLACE_HOLDER, projectName);
        }
        try (PrintWriter printWriter = new PrintWriter(outputFile)) {
            printWriter.write(resultText);
        }
    }

    private void writeText(String[] lines, File file) throws FileNotFoundException {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (String line : lines) {
                printWriter.write(line);
                printWriter.write(System.lineSeparator());
            }
        }
    }
}
