import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ContactWriter {
    public static void saveContact(String fileName, Contact contact) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(contact);
        } catch (IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
}
