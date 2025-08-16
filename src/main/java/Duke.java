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

        ArrayList<String> savedTasks = new ArrayList<>();
        ArrayList<Boolean> tasksDone = new ArrayList<>();

        // Detect user input
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("_________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < savedTasks.size(); i++) {
                    String completed = "";
                    if (tasksDone.get(i)) {
                        completed = "X";
                    } else {
                        completed = " ";
                    }
                    System.out.println(String.valueOf(i + 1) + ".[" + completed + "] "+ savedTasks.get(i));
                }
                System.out.println("_________________________");
            } else if (userInput.length() > 4) {
                if (userInput.substring(0, 4).equals("mark")) {
                    int idx = Character.getNumericValue(userInput.charAt(5)) - 1;
                    tasksDone.set(idx, true);
                    System.out.println("_________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    boolean completed = tasksDone.get(idx);
                    System.out.println("[X] "+ savedTasks.get(idx));
                    System.out.println("_________________________");
                } else if (userInput.substring(0, 6).equals("unmark")) {
                    int idx = Character.getNumericValue(userInput.charAt(7)) - 1;
                    tasksDone.set(idx, false);
                    System.out.println("_________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    boolean completed = tasksDone.get(idx);
                    System.out.println("[ ] "+ savedTasks.get(idx));
                    System.out.println("_________________________");
                }
            } else {
                System.out.println("_________________________");
                System.out.println("added: " + userInput);
                savedTasks.add(userInput);
                tasksDone.add(false);
                System.out.println("_________________________");
            }
        }

    }
}
