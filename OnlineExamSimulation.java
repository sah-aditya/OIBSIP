import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineExamSimulation {

    private static Scanner scanner = new Scanner(System.in);
    private static Timer timer = new Timer();
    private static final int EXAM_DURATION_MINUTES = 10; // Adjust as needed

    private String username;
    private String password;
    private String name;
    private String email;

    public OnlineExamSimulation(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void updateProfile() {
        System.out.print("Enter your name: ");
        this.name = scanner.nextLine();
        System.out.print("Enter your email: ");
        this.email = scanner.nextLine();
    }

    public void changePassword() {
        System.out.print("Enter your new password: ");
        this.password = scanner.nextLine();
        System.out.println("Password updated successfully.");
    }

    public void startExam() {
        String[] questions = {
                "What is the capital of France?",
                "Which programming language is this code written in?",
                "What is the capital of Japan?",
                "What is the largest planet in our solar system?",
                "Who wrote 'Romeo and Juliet'?",
                "What is the main component of the Earth's atmosphere?",
                "Which country is known as the 'Land of the Rising Sun'?",
                "What is the powerhouse of the cell?",
                "Which programming language is known for its association with artificial intelligence?",
                "Who developed the theory of relativity?"
                // Add more questions as needed
        };
        String[] answers = {"Paris", "Java", "Tokyo", "Jupiter", "William Shakespeare", "Nitrogen", "Japan", "Mitochondria", "Python", "Albert Einstein"}; // Correct answers

        int score = 0;
        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i]);
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();

            if (userAnswer.equalsIgnoreCase(answers[i])) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! Correct answer is: " + answers[i]);
            }
        }

        System.out.println("\nYour exam is completed. Your score: " + score + " out of " + questions.length);
    }

    public static void main(String[] args) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        OnlineExamSimulation examSimulator = new OnlineExamSimulation(username, password);

        System.out.println("\nWelcome, " + examSimulator.username + "!");
        examSimulator.updateProfile();
        examSimulator.changePassword();

        // Start the timer
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Automatically submitting the exam.");
                examSimulator.startExam();
                scanner.close(); // Close the scanner
                timer.cancel(); // Cancel the timer
            }
        }, EXAM_DURATION_MINUTES * 60 * 1000); // Convert minutes to milliseconds

        System.out.println("\nLet's start the exam! You have " + EXAM_DURATION_MINUTES + " minutes.");
        examSimulator.startExam();
    }
}
