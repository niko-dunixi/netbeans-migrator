package io.paulbaker.school.netbeans.sourcefiles;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.function.Consumer;

/**
 * @author Paul Nelson Baker
 * @see <a href="https://github.com/paul-nelson-baker/">GitHub</a>
 * @see <a href="https://www.linkedin.com/in/paul-n-baker/">LinkedIn</a>
 * @since 2018-07
 * WGU Student Info: pbake22 - #000412290
 */
@AllArgsConstructor
public class SourceFileCopier {

    private File sourceDirectory;
    private File destinationProjectDirectory;

    public void copy() throws IOException {
        File mainSourcesOutputDirectory = new File(destinationProjectDirectory, "src");
        File testSourcesOutputDirectory = new File(destinationProjectDirectory, "test");

        copySources(new File(sourceDirectory, "src/main/java"), mainSourcesOutputDirectory);
        copySources(new File(sourceDirectory, "src/main/resources"), mainSourcesOutputDirectory);
        copySources(new File(sourceDirectory, "src/test/java"), testSourcesOutputDirectory);
        copySources(new File(sourceDirectory, "src/test/resources"), testSourcesOutputDirectory);
    }

    private static void copySources(File inputSourcesDirectory, File outputSourcesDirectory) throws IOException {
        if (!inputSourcesDirectory.exists()) {
            return;
        }
        Files.walk(inputSourcesDirectory.toPath())
                .filter(path -> path.toFile().isFile())
                .forEach(copyFileToDestination(inputSourcesDirectory, outputSourcesDirectory));
    }

    private static Consumer<Path> copyFileToDestination(File inputDirectory, File mainSourcesOutputDirectory) {
        return path -> {
            Path subpath = path.subpath(inputDirectory.toPath().getNameCount(), path.getNameCount());
            File destinationFile = new File(mainSourcesOutputDirectory, subpath.toString());
            //noinspection ResultOfMethodCallIgnored
            destinationFile.getParentFile().mkdirs();
            try {
                Files.copy(path, destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
