import java.io.*;
import java.util.List;

public class LargefileWriter {
    public static void writeProcessedData(String outputFileName, List<String> processedData) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for(String data : processedData) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
}
