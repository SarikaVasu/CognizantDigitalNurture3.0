import java.util.Hashtable;
import java.util.Map;

public class ContactManagement {
    private Hashtable<Integer, Contact> contacts = new Hashtable<>();
    public void addContact(Contact contact) {
        contacts.put(contact.getId(), contact);
    }
    public Contact removeContact(int contactId) {
        if(contacts.containsKey(contactId)) {
            return contacts.remove(contactId);
        }
        return null;
    }
    public void displayProducts() {
        if(!contacts.isEmpty()) {
            for(Map.Entry<Integer, Contact> contactEntry : contacts.entrySet()) {
                System.out.println(contactEntry.getValue());
            }
        }
    }
}
