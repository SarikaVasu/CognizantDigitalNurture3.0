import java.io.*;

public class LogReader {
    public static void readLogs(String fileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String log;
            while((log = reader.readLine()) != null) {
                System.out.println(log);
            }
        } catch(FileNotFoundException err) {
            System.out.println("File not found: " + err.getMessage());
        } catch (IOException err) {
            System.out.println("Error: " + err);
        }
    }
}
