import java.util.TreeSet;

public class UserRegistration {
    private TreeSet<String> users = new TreeSet<>();
    public void registerUser(String userName) {
        users.add(userName);
    }
    public void removeuser(String userName) {
        users.remove(userName);
    }
    public void displayUsers() {
        System.out.println(users);
    }
}
