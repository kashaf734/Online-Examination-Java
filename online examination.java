
import java.util.Scanner;

public class OnlineExaminationSystem {
    private String username;
    private String password;
    private boolean isLoggedIn;
    private int questionCount;
    private String[] questions;
    private String[][] options;
    private int[] correctAnswers;
    private int[] userAnswers;

    public OnlineExaminationSystem(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("Successfully You are registered! :)");
        this.isLoggedIn = false;
        this.questionCount = 5; // Reduced the number of questions for demonstration
        this.questions = new String[questionCount];
        this.options = new String[questionCount][2]; // Assuming each question has 2 options for demonstration
        this.correctAnswers = new int[questionCount];
        this.userAnswers = new int[questionCount];
        // initialize questions, options, and correct answers
        initializeQuestions();
    }

    private void initializeQuestions() {
        questions[0] = "What is 2 + 2?";
        options[0][0] = "A. 3";
        options[0][1] = "B. 4";
        correctAnswers[0] = 1; // B. 4

        questions[1] = "What is the capital of France?";
        options[1][0] = "A. London";
        options[1][1] = "B. Paris";
        correctAnswers[1] = 2; // B. Paris

        questions[2] = "Who is the current President of the United States?";
        options[2][0] = "A. Barack Obama";
        options[2][1] = "B. Joe Biden";
        correctAnswers[2] = 2; // B. Joe Biden

        questions[3] = "What is the chemical symbol for water?";
        options[3][0] = "A. H2O";
        options[3][1] = "B. CO2";
        correctAnswers[3] = 1; // A. H2O

        questions[4] = "Which planet is known as the Red Planet?";
        options[4][0] = "A. Venus";
        options[4][1] = "B. Mars";
        correctAnswers[4] = 2; // B. Mars
    }

    public void login() {
        System.out.println("Log in to give the Exam ");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Password: ");
        String inputPassword = scanner.nextLine();
        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            isLoggedIn = true;
            System.out.println("Login successful. Best of Luck Dear");
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }

    public void logout() {
        isLoggedIn = false;
        System.out.println("Logout successful.");
    }

    public void startExam() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("You have 5 seconds to answer each question.");
        for (int i = 0; i < questionCount; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i]);
            System.out.println(options[i][0]);
            System.out.println(options[i][1]);
            System.out.print("Your answer (A/B): ");
            String answer = scanner.nextLine().toUpperCase();
            userAnswers[i] = answer.equals("A") ? 1 : 2;
            // Timer for each question
            try {
                Thread.sleep(5000); // Sleep for 5 seconds
            } catch (InterruptedException e) {
                // Timer interrupted
            }
        }
        System.out.println("\nAll questions answered. Automatically submitting the exam...");
        submitExam();
    }

    public void submitExam() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        int score = 0;
        for (int i = 0; i < questionCount; i++) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }
        System.out.println("Your score is " + score + " out of " + questionCount + ".");
        System.out.println("Best of luck :)");
        logout();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username and password");
        String uName = sc.nextLine();
        String pWord = sc.nextLine();
        OnlineExaminationSystem examSystem = new OnlineExaminationSystem(uName, pWord);
        examSystem.login();
        examSystem.startExam();
    }
}
