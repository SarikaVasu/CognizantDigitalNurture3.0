import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
    public static void saveNoteToFile(String fileName, String note) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(note);
        } catch(IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
}
