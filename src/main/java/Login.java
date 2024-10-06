import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Login {
    String username;
    String password;
    String role;

    public Login(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static Login authenticate(String username, String password) {
        String fileLocation = "./src/main/resources/users.json";
        JSONParser jsonParser = new JSONParser();

        try {
            // Parse the JSON file into an array
            File file = new File(fileLocation);
            FileReader reader = new FileReader(file);
            JSONArray usersArray = (JSONArray) jsonParser.parse(reader);

            // Iterate through the users array
            for (Object userObject : usersArray) {
                JSONObject userJson = (JSONObject) userObject;
                String userJsonUsername = (String) userJson.get("username");
                String userJsonPassword = (String) userJson.get("password");
                String userJsonRole = (String) userJson.get("role");

                // Check if the credentials match
                if (userJsonUsername.equals(username) && userJsonPassword.equals(password)) {
                    return new Login(userJsonUsername, userJsonPassword, userJsonRole);
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null; // Return null if no match is found
    }
}
