import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
    public static void writeLog(String fileName, String logMessage) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(logMessage);
        } catch (IOException err) {
            System.out.println("Error: " + err);
        }
    }
}
