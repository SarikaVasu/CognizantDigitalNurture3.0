import java.io.*;

public class FileReaderDemo {
    public static void readNoteFromFile(String fileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String notes = "";
            while((notes = reader.readLine()) != null) {
                System.out.println(notes);
            }
        } catch(IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
}
