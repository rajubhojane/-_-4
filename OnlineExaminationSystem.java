import java.util.Scanner;

class User {
    private String username;
    private String password;
    private String profile;

    public User(String username, String password, String profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password updated successfully.");
    }

    public void updateProfile(String newProfile) {
        this.profile = newProfile;
        System.out.println("Profile updated successfully.");
    }

    public String getProfile() {
        return profile;
    }
}

class Examination {
    private String[] questions;
    private String[] options;
    private char[] correctAnswers;

    public Examination() {
        questions = new String[]{"Question 1", "Question 2", "Question 3", "Question 4", "Question 5"};
        options = new String[]{"A) Option A", "B) Option B", "C) Option C", "D) Option D"};
        correctAnswers = new char[]{'A', 'B', 'C', 'D', 'A'};
    }

    public void startExam(User user) {
        System.out.println("Welcome, " + user.getUsername() + "! You may begin the exam.");
        int score = 0;
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i]);
            for (String option : options) {
                System.out.println(option);
            }
            System.out.print("Your answer (A/B/C/D): ");
            char answer = scanner.next().charAt(0);

            if (Character.toUpperCase(answer) == correctAnswers[i]) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }
        }

        System.out.println("\nYour score: " + score + " out of " + questions.length);
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a user account
        User user = new User("username", "password", "profile");

        // Login
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (!user.getUsername().equals(username) || !user.validatePassword(password)) {
            System.out.println("Invalid username or password. Exiting...");
            System.exit(0);
        }

        // Menu
        boolean running = true;
        while (running) {
            System.out.println("\nOnline Examination System Menu:");
            System.out.println("1. Update Profile");
            System.out.println("2. Update Password");
            System.out.println("3. Start Exam");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new profile: ");
                    String newProfile = scanner.nextLine();
                    user.updateProfile(newProfile);
                    break;
                case 2:
                    System.out.print("Enter current password: ");
                    String currentPassword = scanner.nextLine();
                    if (user.validatePassword(currentPassword)) {
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.nextLine();
                        user.updatePassword(newPassword);
                    } else {
                        System.out.println("Incorrect password. Profile update failed.");
                    }
                    break;
                case 3:
                    Examination exam = new Examination();
                    exam.startExam(user);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }

        System.out.println("Logged out successfully. Goodbye!");
        scanner.close();
    }
}
