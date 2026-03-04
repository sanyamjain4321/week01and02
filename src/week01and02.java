import java.util.*;

public class week01and02 {

    public static void main(String[] args) {

        UsernameChecker checker = new UsernameChecker();

        checker.registerUser("john_doe", 1);

        System.out.println("Is john_doe available? " + checker.checkAvailability("john_doe"));
        System.out.println("Is jane_smith available? " + checker.checkAvailability("jane_smith"));

        System.out.println("Suggestions: " + checker.suggestAlternatives("john_doe"));

        System.out.println("Most attempted username: " + checker.getMostAttempted());
    }
}

class UsernameChecker {

    private Map<String, Integer> usernameMap = new HashMap<>();
    private Map<String, Integer> attemptFrequency = new HashMap<>();

    public boolean checkAvailability(String username) {

        attemptFrequency.put(username,
                attemptFrequency.getOrDefault(username, 0) + 1);

        return !usernameMap.containsKey(username);
    }

    public void registerUser(String username, int userId) {
        usernameMap.put(username, userId);
    }

    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            String alt = username + i;

            if (!usernameMap.containsKey(alt)) {
                suggestions.add(alt);
            }
        }

        suggestions.add(username.replace("_", "."));

        return suggestions;
    }

    public String getMostAttempted() {

        String maxUser = null;
        int max = 0;

        for (Map.Entry<String, Integer> entry : attemptFrequency.entrySet()) {

            if (entry.getValue() > max) {
                max = entry.getValue();
                maxUser = entry.getKey();
            }
        }

        return maxUser;
    }
}