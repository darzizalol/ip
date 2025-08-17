import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String initMessage = "_________________________\n" +
                "Hello! I'm ChatZH\n" +
                "What can I do for you?\n" +
                "_________________________\n";

        System.out.println(initMessage);

        ArrayList<Task> savedTasks = new ArrayList<>();

        // Detect user inputs
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("_________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < savedTasks.size(); i++) {
                    Task task = savedTasks.get(i);
                    System.out.println(String.valueOf(i + 1) + "." + task.getStatusText());
                };
                System.out.println("_________________________");
            }

            if (userInput.startsWith("mark")) {
                int idx = Character.getNumericValue(userInput.charAt(5)) - 1;
                Task task = savedTasks.get(idx);
                task.setStatus(true);
                System.out.println("_________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] "+ task.getDescription());
                System.out.println("_________________________");
            } else if (userInput.startsWith("unmark")) {
                int idx = Character.getNumericValue(userInput.charAt(7)) - 1;
                Task task = savedTasks.get(idx);
                task.setStatus(false);
                System.out.println("_________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] "+ task.getDescription());
                System.out.println("_________________________");
            } else if (userInput.startsWith("todo")) {
                System.out.println("_________________________");
                System.out.println("Got it. I've added this task:");

                String[] parts = userInput.split(" ", 2);
                String rest = parts.length > 1 ? parts[1] : "";
                ToDos todo = new ToDos(rest);
                savedTasks.add(todo);
                System.out.println(todo.getStatusText());

                System.out.println("Now you have " + savedTasks.size() + " tasks in the list.");
                System.out.println("_________________________");
            } else if (userInput.startsWith("deadline")) {
                System.out.println("_________________________");
                System.out.println("Got it. I've added this task:");

                String[] parts = userInput.split(" ", 2);
                String rest = parts.length > 1 ? parts[1] : "";

                String[] parts2 = rest.split(" /by ", 2);
                String description = parts2[0];
                String deadline = parts2[1];
                Deadlines ddl = new Deadlines(description, deadline);
                savedTasks.add(ddl);
                System.out.println(ddl.getStatusText());

                System.out.println("Now you have " + savedTasks.size() + " tasks in the list.");
                System.out.println("_________________________");
            } else if (userInput.startsWith("event")) {
                System.out.println("_________________________");
                System.out.println("Got it. I've added this task:");

                String[] parts = userInput.split(" ", 2);
                String rest = parts.length > 1 ? parts[1] : "";

                String[] parts2 = rest.split(" /from ", 2);
                String description = parts2[0];
                String times = parts2[1];
                String[] timeParts = times.split(" /to ", 2);
                String startTime = timeParts[0];
                String endTime = timeParts[1];
                Events event = new Events(description, startTime, endTime);
                savedTasks.add(event);
                System.out.println(event.getStatusText());

                System.out.println("Now you have " + savedTasks.size() + " tasks in the list.");
                System.out.println("_________________________");
            }
        }
    }
}
