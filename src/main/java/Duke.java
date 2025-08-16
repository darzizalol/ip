import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String initMessage = "_________________________ \n" +
                "Hello! I'm ChatZH \n" +
                "What can I do for you? \n" +
                "_________________________ \n";

        System.out.println(initMessage);

        ArrayList<String> savedTexts = new ArrayList<>();

        // Detect user input
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("_________________________");
                for (int i = 0; i < savedTexts.size(); i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + savedTexts.get(i));
                }
                System.out.println("_________________________");
            } else {
                System.out.println("_________________________");
                System.out.println("added: " + userInput);
                savedTexts.add(userInput);
                System.out.println("_________________________");
            }
        }

    }
}
