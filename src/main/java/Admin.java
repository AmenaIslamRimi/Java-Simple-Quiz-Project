import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends Login{
    public Admin(String username, String password, String role) {
        super(username, password, role);
    }
    public void addQuestion() throws IOException, ParseException {
        String fileLocation = "./src/main/resources/quiz.json";
        JSONParser parser = new JSONParser();
        JSONArray quesArray = new JSONArray(); // Initialize an empty array

        File file = new File(fileLocation);

        // Check if the file exists and has valid JSON content
        if (file.exists() && file.length() != 0) {
            try (FileReader reader = new FileReader(file)) {
                quesArray = (JSONArray) parser.parse(reader);
            } catch (ParseException | IOException e) {
                System.out.println("Error parsing or reading quiz.json. Initializing a new file.");
                quesArray = new JSONArray(); // Reinitialize in case of parse error
            }
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("System:> Input your question");
            String question = scanner.nextLine();

            System.out.println("System: Input option 1:");
            String option1 = scanner.nextLine();
            System.out.println("System: Input option 2:");
            String option2 = scanner.nextLine();
            System.out.println("System: Input option 3:");
            String option3 = scanner.nextLine();
            System.out.println("System: Input option 4:");
            String option4 = scanner.nextLine();

            System.out.println("System: What is the answer key?");
            String answerKey = scanner.nextLine();

            JSONObject questionObject = new JSONObject();
            questionObject.put("question", question);
            questionObject.put("option1", option1);
            questionObject.put("option2", option2);
            questionObject.put("option3", option3);
            questionObject.put("option4", option4);
            questionObject.put("answerKey", answerKey);

            quesArray.add(questionObject);

            System.out.println(
                    "System:> Saved successfully! Do you want to add more questions? (press s for start and q for quit)"
            );
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("q")) {
                break;
            }
        }

        // Write to file
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(quesArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("System:> Questions added successfully!");
        }
    }
}
