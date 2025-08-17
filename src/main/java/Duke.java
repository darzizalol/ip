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
            
            try {
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equals("list")) {
                    handleListCommand(savedTasks);
                } else if (userInput.startsWith("mark")) {
                    handleMarkCommand(userInput, savedTasks, true);
                } else if (userInput.startsWith("unmark")) {
                    handleMarkCommand(userInput, savedTasks, false);
                } else if (userInput.startsWith("todo")) {
                    handleTodoCommand(userInput, savedTasks);
                } else if (userInput.startsWith("deadline")) {
                    handleDeadlineCommand(userInput, savedTasks);
                } else if (userInput.startsWith("event")) {
                    handleEventCommand(userInput, savedTasks);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("_________________________");
                System.out.println("OOPS!!! " + e.getMessage());
                System.out.println("_________________________");
            } catch (Exception e) {
                System.out.println("_________________________");
                System.out.println("OOPS!!! An unexpected error occurred: " + e.getMessage());
                System.out.println("_________________________");
            }
        }
    }

    private static void handleListCommand(ArrayList<Task> savedTasks) {
        System.out.println("_________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < savedTasks.size(); i++) {
            Task task = savedTasks.get(i);
            System.out.println(String.valueOf(i + 1) + "." + task.getStatusText());
        }
        System.out.println("_________________________");
    }

    private static void handleMarkCommand(String userInput, ArrayList<Task> savedTasks, boolean markAsDone) throws DukeException {
        if (savedTasks.isEmpty()) {
            throw new DukeException("There are no tasks to mark!");
        }

        int idx;
        try {
            if (markAsDone) {
                idx = Character.getNumericValue(userInput.charAt(5)) - 1;
            } else {
                idx = Character.getNumericValue(userInput.charAt(7)) - 1;
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please specify a task number to mark!");
        }

        if (idx < 0 || idx >= savedTasks.size()) {
            throw new DukeException("Invalid task number! Please use a number between 1 and " + savedTasks.size());
        }

        Task task = savedTasks.get(idx);
        task.setStatus(markAsDone);
        
        System.out.println("_________________________");
        if (markAsDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task.getStatusText());
        System.out.println("_________________________");
    }

    private static void handleTodoCommand(String userInput, ArrayList<Task> savedTasks) throws DukeException {
        String[] parts = userInput.split(" ", 2);
        String description = parts.length > 1 ? parts[1].trim() : "";
        
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        ToDos todo = new ToDos(description);
        savedTasks.add(todo);
        
        System.out.println("_________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(todo.getStatusText());
        System.out.println("Now you have " + savedTasks.size() + " tasks in the list.");
        System.out.println("_________________________");
    }

    private static void handleDeadlineCommand(String userInput, ArrayList<Task> savedTasks) throws DukeException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String rest = parts[1].trim();
        if (rest.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String[] parts2 = rest.split(" /by ", 2);
        if (parts2.length < 2) {
            throw new DukeException("Please specify a deadline using /by keyword.");
        }

        String description = parts2[0].trim();
        String deadline = parts2[1].trim();
        
        if (description.isEmpty() || deadline.isEmpty()) {
            throw new DukeException("Both description and deadline cannot be empty.");
        }

        Deadlines ddl = new Deadlines(description, deadline);
        savedTasks.add(ddl);
        
        System.out.println("_________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(ddl.getStatusText());
        System.out.println("Now you have " + savedTasks.size() + " tasks in the list.");
        System.out.println("_________________________");
    }

    private static void handleEventCommand(String userInput, ArrayList<Task> savedTasks) throws DukeException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        String rest = parts[1].trim();
        if (rest.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        String[] parts2 = rest.split(" /from ", 2);
        if (parts2.length < 2) {
            throw new DukeException("Please specify start time using /from keyword.");
        }

        String description = parts2[0].trim();
        String times = parts2[1].trim();
        
        String[] timeParts = times.split(" /to ", 2);
        if (timeParts.length < 2) {
            throw new DukeException("Please specify end time using /to keyword.");
        }

        String startTime = timeParts[0].trim();
        String endTime = timeParts[1].trim();
        
        if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new DukeException("Description, start time, and end time cannot be empty.");
        }

        Events event = new Events(description, startTime, endTime);
        savedTasks.add(event);
        
        System.out.println("_________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(event.getStatusText());
        System.out.println("Now you have " + savedTasks.size() + " tasks in the list.");
        System.out.println("_________________________");
    }
}
