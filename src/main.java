import java.util.List;
import java.util.Scanner;

public class CriminalFaceDetectionApp {
    private UserRepository userRepository;
    private CriminalRepository criminalRepository;
    private FaceRecognitionService faceRecognitionService;
    private AlertService alertService;

    public CriminalFaceDetectionApp(UserRepository userRepository, CriminalRepository criminalRepository,
                                    FaceRecognitionService faceRecognitionService, AlertService alertService) {
        this.userRepository = userRepository;
        this.criminalRepository = criminalRepository;
        this.faceRecognitionService = faceRecognitionService;
        this.alertService = alertService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Criminal Face Detection Application!");

        // User login/registration logic
        User currentUser = null;
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // User registration
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    User newUser = new User(username, password);
                    userRepository.createUser(newUser);
                    System.out.println("Registration successful!");
                    break;

                case 2:
                    // User login
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    User existingUser = userRepository.getUserByUsername(loginUsername);
                    if (existingUser != null && existingUser.getPassword().equals(loginPassword)) {
                        currentUser = existingUser;
                        loggedIn = true;
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Invalid username or password!");
                    }
                    break;

                case 3:
                    // Exit the application
                    System.out.println("Exiting the application...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        // Main menu
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu");
            System.out.println("1. Add Criminal");
            System.out.println("2. Search Criminals");
            System.out.println("3. Detect Faces");
            System.out.println("4. Generate Alerts");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Add criminal
                    if (currentUser.isAdmin()) {
                        System.out.print("Enter criminal's name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter criminal's age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();

                        Criminal newCriminal = new Criminal(name, age);
                        criminalRepository.addCriminal(newCriminal);
                        System.out.println("Criminal added successfully!");
                    } else {
                        System.out.println("Access denied! Only administrators can add criminals.");
                    }
                    break;

                case 2:
                    // Search criminals
                    System.out.print("Enter criminal's name: ");
                    String searchName = scanner.nextLine();
                    System.out.print("Enter criminal's age: ");
                    int searchAge = scanner.nextInt();
                    scanner.nextLine();

                    List<Criminal> searchResults = criminalRepository.searchCriminals(searchName, searchAge);
                    if (!searchResults.isEmpty()) {
                        System.out.println("Search results:");
                        for (Criminal criminal : searchResults) {
                            System.out.println("Name: " + criminal.getName());
                            System.out.println("Age: " + criminal.getAge());
                            // Display other criminal details
                            System.out.println("---------------------");
                        }
                    } else {
                        System.out.println("No criminals found matching the search criteria.");
                    }
                    break;

                case 3:
                    // Detect faces
                    System.out.println("Enter the path to the image file: ");
                    String imagePath = scanner.nextLine();

                    // Call face detection method from FaceRecognitionService
                    boolean faceDetected = faceRecognitionService.detectFace(imagePath);
                    if (faceDetected) {
                        System.out.println("Face detected in the image!");
                    } else {
                        System.out.println("No face detected in the image.");
                    }
                    break;

                case 4:
                    // Generate alerts
                    List<Criminal> criminals = criminalRepository.getAllCriminals();

                    // Call face comparison method from FaceRecognitionService
                    List<Criminal> potentialMatches = faceRecognitionService.compareFaces(imagePath, criminals);

                    // Generate alerts for potential matches
                    for (Criminal criminal : potentialMatches) {
                        alertService.generateAlert(criminal);
                    }
                    System.out.println("Alerts generated for potential matches.");
                    break;

                case 5:
                    // Logout
                    currentUser = null;
                    loggedIn = false;
                    System.out.println("Logged out successfully!");
                    break;

                case 6:
                    // Exit the application
                    exit = true;
                    System.out.println("Exiting the application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        // Initialize repositories, services, and dependencies
        UserRepository userRepository = new UserRepositoryImpl();
        CriminalRepository criminalRepository = new CriminalRepositoryImpl();
        FaceRecognitionService faceRecognitionService = new FaceRecognitionServiceImpl();
        AlertService alertService = new AlertServiceImpl();

        // Create an instance of the CriminalFaceDetectionApp and start the application
        CriminalFaceDetectionApp app = new CriminalFaceDetectionApp(userRepository, criminalRepository,
                faceRecognitionService, alertService);
        app.start();
    }
}


