import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Quiz {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("System:> Enter your username");
        String username = scanner.nextLine();

        System.out.println("System:> Enter password");
        String password = scanner.nextLine();

        Login user = Login.authenticate(username, password);

        if (user != null) {
            if (user.role.equals("admin")) {
                System.out.println(
                        "System:> Welcome " +
                                username +
                                "! Please create new questions in the question bank."
                );
                Admin admin = new Admin(username, password, "admin");
                admin.addQuestion();
            } else if (user.role.equals("student")) {
                System.out.println(
                        "System:> Welcome " +
                                username +
                                " to the quiz! We will throw you 10 questions. Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start."
                );
                String start = scanner.nextLine();
                if (start.equalsIgnoreCase("s")) {
                    Student student = new Student(username, password, "student");
                    student.giveQuiz();
                }
            }
        } else {
            System.out.println("Invalid login credentials. Please try again.");
        }
    }
}
