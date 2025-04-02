import java.util.regex.*;
import java.util.*;

public class whichCouldNull {
    public static void main(String[] args) {
        // Example input string
        String input = "Hello @user-name, welcome to @java_programming!";

        // Regular expression to match username mentions
        String regex = "@([a-zA-Z0-9_-]+)";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(input);

        // List to store extracted usernames
        List<String> usernames = new ArrayList<>();

        // Find matches and add them to the list
        while (matcher.find()) {
            System.out.println("matcher.group " + matcher.group());
            usernames.add(matcher.group(1)); // Extract the username after "@"
        }

        // Print the extracted usernames
        System.out.println("Extracted usernames: " + usernames);
    }
}