public class User {
    private String username;Criminal.java
    private String password;
    // Other user attributes and methods

    // Getters and setters
}

UserRepository.java (Interface for user data access)
        ```java
public interface UserRepository {
    void createUser(User user);
    User getUserByUsername(String username);
    // Other methods for user management
}
