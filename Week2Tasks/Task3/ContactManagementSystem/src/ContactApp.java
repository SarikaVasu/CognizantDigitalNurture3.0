import java.util.Scanner;

public class ContactApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        Contact contact = new Contact("ABC", "1234567890", "jgmghdk@gmail.com");
        ContactWriter.saveContact(fileName, contact);
        System.out.println("Saved");
        System.out.println("Reading...");
        Contact readContact = ContactReader.readContact(fileName);
        if(readContact != null) {
            System.out.println(readContact);
        }

        ContactReader.readContact("fil.txt");
    }
}
