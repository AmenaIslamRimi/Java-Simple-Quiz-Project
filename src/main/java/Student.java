import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

public class Student extends Login {
    public Student(String username, String password, String role) {
        super(username, password, role);
    }

    public void giveQuiz() {
        String fileLocation = "./src/main/resources/quiz.json";
        JSONParser jsonParser = new JSONParser();

        try {
            // Parse the quiz JSON file into an array
            FileReader reader = new FileReader(fileLocation);
            JSONArray quizArray = (JSONArray) jsonParser.parse(reader);

            // Convert the JSONArray to an ArrayList for shuffling
            ArrayList<JSONObject> questionsList = new ArrayList<>();
            for (int i = 0; i < quizArray.size(); i++) {
                questionsList.add((JSONObject) quizArray.get(i));
            }
            Collections.shuffle(questionsList);

            Scanner inputScanner = new Scanner(System.in);
            int score = 0;

            // Loop through the shuffled list of questions
            for (int i = 0; i < Math.min(10, questionsList.size()); i++) {
                JSONObject questionObject = questionsList.get(i);

                // Extract question and options
                String questionText = (String) questionObject.get("question");
                String option1 = (String) questionObject.get("option1");
                String option2 = (String) questionObject.get("option2");
                String option3 = (String) questionObject.get("option3");
                String option4 = (String) questionObject.get("option4");
                String answerKey = (String) questionObject.get("answerKey");

                // Display the question and options
                System.out.printf("System:> [%d] %s\n", i + 1, questionText);
                System.out.println("1. " + option1);
                System.out.println("2. " + option2);
                System.out.println("3. " + option3);
                System.out.println("4. " + option4);

                // Get the student's answer
                String studentAnswer = inputScanner.nextLine();

                // Check if the answer is correct
                if (studentAnswer.equalsIgnoreCase(answerKey.replace("option ", "").trim())) {
                    score++;
                } else {
                    // Debugging output
                    System.out.println("Your answer: " + studentAnswer + ", Correct answer: " + answerKey);
                }
            }

            // Output the result based on the score
            if (score >= 8) {
                System.out.println("Excellent! You have got " + score + " out of 10");
            } else if (score >= 5) {
                System.out.println("Good. You have got " + score + " out of 10");
            } else if (score >= 2) {
                System.out.println("Very poor! You have got " + score + " out of 10");
            } else {
                System.out.println("Very sorry you are failed. You have got " + score + " out of 10");
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the quiz.json file.");
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
