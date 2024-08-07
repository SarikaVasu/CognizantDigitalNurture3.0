import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProcessorApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputFile = sc.nextLine();
        String outputFile = sc.nextLine();
        System.out.println("Reading...");
        LargefileReader.readLargeFile(inputFile);
        List<String> processedData = processData(inputFile);
        System.out.println("Writing...");
        LargefileWriter.writeProcessedData(outputFile, processedData);
        LargefileReader.readLargeFile(outputFile);

        LargefileReader.readLargeFile("fil.txt");

    }
    private static List<String> processData(String inputFile) {
        List<String> processedData = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String content;
            while((content = reader.readLine()) != null) {
                if(content.contains("AA")) {
                    processedData.add(content);
                }
            }
        } catch (IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
        return processedData;
    }
}
