import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class BackupApp {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("Src dir");
        String srcDir = sc.nextLine();
        Path sourceDir = Paths.get(srcDir);

        System.out.println("Target dir");
        String trgDir = sc.nextLine();
        Path targetDir = Paths.get(trgDir);

        System.out.println("Backing up: ");
        DirectoryCopy.copyDirectory(sourceDir, targetDir);
    }
}
