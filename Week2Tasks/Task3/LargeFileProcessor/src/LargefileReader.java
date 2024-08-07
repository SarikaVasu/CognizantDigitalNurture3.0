import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LargefileReader {
    public static void readLargeFile(String inputFileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String content;
            while((content = reader.readLine()) != null) {
                System.out.println(content);
            }
        } catch (IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
}
