import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class DirectoryCopy {
    public static void copyDirectory(Path sourceDir, Path targetDir) {
        try(Stream<Path> paths = Files.walk(sourceDir)) {
            paths.forEach(sourcePath -> {
                Path targetPath = targetDir.resolve(sourceDir.relativize(sourcePath));
                try {
                    if(Files.isDirectory(sourcePath)) {
                        if(!Files.exists(targetPath)) {
                            Files.createDirectories(targetPath);
                        }
                    } else {
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException err) {
                    System.out.println("Error: " + err);
                }
            });
        } catch (IOException err) {
            System.out.println("Error: " + err);
        }
    }
}
