import java.util.Scanner;

public class LogApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        String log1 = scanner.nextLine();
        String log2 = scanner.nextLine();
        LogWriter.writeLog(fileName, log1);
        LogWriter.writeLog(fileName, log2);
        System.out.println("Logs entered");
        System.out.println("Reading logs.....");
        LogReader.readLogs(fileName);
        LogReader.readLogs("text.txt");
    }
}
