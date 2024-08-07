import java.util.Scanner;

public class NoteApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter notes: ");
        String notes = scanner.nextLine();
        String fileName = "notes.txt";
        FileWriterDemo.saveNoteToFile(fileName, notes);
        System.out.println("Saved successfully.");
        System.out.println("Message saved: ");
        FileReaderDemo.readNoteFromFile(fileName);
        scanner.close();
    }
}
