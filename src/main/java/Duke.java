import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String initMessage = "_________________________ \n" +
                "Hello! I'm ChatZH \n" +
                "What can I do for you? \n" +
                "_________________________ \n";

        System.out.println(initMessage);

        // Detect user input
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println("_________________________");
            System.out.println(userInput);
            System.out.println("_________________________");
        }

    }
}
