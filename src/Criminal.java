public class Criminal {
}
public class Criminal {
    private String name;
    private int age;
    // Other criminal attributes and methods

    // Getters and setters
}

CriminalRepository.java (Interface for criminal data access)
        ```java
public interface CriminalRepository {
    void addCriminal(Criminal criminal);
    List<Criminal> searchCriminals(String name, int age);
    // Other methods for criminal management
}
