import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ContactReader {
    public static Contact readContact(String fileName) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Contact) ois.readObject();
        } catch(FileNotFoundException err) {
            System.out.println("Error: " + err.getMessage());
        } catch(ClassNotFoundException err) {
            System.out.println("Error: " + err.getMessage());
        } catch(ClassCastException err) {
            System.out.println("Error: " + err.getMessage());
        }catch(IOException err) {
            System.out.println("Error: " + err.getMessage());
        }
        return null;
    }
}
