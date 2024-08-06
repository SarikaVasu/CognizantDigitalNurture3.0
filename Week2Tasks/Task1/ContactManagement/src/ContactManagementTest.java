public class ContactManagementTest {
    public static void main(String[] args) {
        Contact contact1 = new Contact(1, "abc", "1234567890");
        Contact contact2 = new Contact(2, "abc2", "1234567890");
        Contact contact3 = new Contact(3, "abc3", "1234567890");
        Contact contact4 = new Contact(4, "abc4", "1234567890");
        ContactManagement contacts = new ContactManagement();
        contacts.addContact(contact1);
        contacts.addContact(contact2);
        contacts.addContact(contact3);
        contacts.addContact(contact4);
        contacts.displayProducts();
        contacts.removeContact(3);
        contacts.displayProducts();
    }
}
