import java.util.Scanner;

public class UserInterface {
    private UserRepository userRepository;
    private CriminalRepository criminalRepository;
    private FaceRecognitionService faceRecognitionService;
    private AlertService alertService;

    public UserInterface(UserRepository userRepository, CriminalRepository criminalRepository,
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
        // Implement the necessary user interface logic for login and registration

        // Main menu
