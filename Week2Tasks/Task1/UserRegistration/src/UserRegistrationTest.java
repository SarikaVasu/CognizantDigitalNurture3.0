public class UserRegistrationTest {
    public static void main(String[] args) {
        UserRegistration users = new UserRegistration();
        users.registerUser("Sarika");
        users.registerUser("Abhi");
        users.registerUser("Tamil");
        users.registerUser("Nithya");
        users.displayUsers();
        users.registerUser("Sarika");
        users.displayUsers();
    }
}
