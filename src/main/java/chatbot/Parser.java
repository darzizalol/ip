package chatbot;
import java.util.ArrayList;

public class Parser {

    /**
     * Handle the user command, log any errors, and determine the command type,
     * and triggers the respective handler methods
     * 
     * @param userInput the user input
     * @param savedTasks the saved tasks
     */
    public static void handleUserCommand(String userInput, ArrayList<Task> savedTasks) {
        try {
            if (userInput.equals("list")) {
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
            } else if (userInput.startsWith("delete")){
                handleDeleteCommand(userInput, savedTasks);
            } else if (userInput.startsWith("find")) {
                handleFindCommand(userInput, savedTasks);
            } else {
                throw new ChatZHException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (ChatZHException e) {
            System.out.println("_________________________");
            System.out.println("OOPS!!! " + e.getMessage());
            System.out.println("_________________________");
        } catch (Exception e) {
            System.out.println("_________________________");
            System.out.println("OOPS!!! An unexpected error occurred: " + e.getMessage());
            System.out.println("_________________________");
        }
    }

    /**
     * Handle the list command, print all the saved tasks in the list
     * 
     * @param savedTasks the saved tasks
     */
    private static void handleListCommand(ArrayList<Task> savedTasks) {
        System.out.println("_________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < savedTasks.size(); i++) {
            Task task = savedTasks.get(i);
            System.out.println(String.valueOf(i + 1) + "." + task.getStatusText());
        }
        System.out.println("_________________________");
    }

    /**
     * Handle the mark command, mark the task as done or not done
     * 
     * @param userInput the user input
     * @param savedTasks the saved tasks
     * @param markAsDone whether to mark the task as done
     */
    private static void handleMarkCommand(String userInput, ArrayList<Task> savedTasks, boolean markAsDone) throws ChatZHException {
        if (savedTasks.isEmpty()) {
            throw new ChatZHException("There are no tasks to mark!");
        }

        int idx;
        try {
            if (markAsDone) {
                idx = Character.getNumericValue(userInput.charAt(5)) - 1;
            } else {
                idx = Character.getNumericValue(userInput.charAt(7)) - 1;
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new ChatZHException("Please specify a task number to mark!");
        }

        if (idx < 0 || idx >= savedTasks.size()) {
            throw new ChatZHException("Invalid task number! Please use a number between 1 and " + savedTasks.size());
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

    /**
     * Handle the todo command, add a todo task to the list
     * 
     * @param userInput the user input
     * @param savedTasks the saved tasks
     */
    private static void handleTodoCommand(String userInput, ArrayList<Task> savedTasks) throws ChatZHException {
        String[] parts = userInput.split(" ", 2);
        String description = parts.length > 1 ? parts[1].trim() : "";
        
        if (description.isEmpty()) {
            throw new ChatZHException("The description of a todo cannot be empty.");
        }

        ToDos todo = new ToDos(description, false);
        savedTasks.add(todo);
        
        System.out.println("_________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(todo.getStatusText());
        System.out.println("Now you have " + savedTasks.size() + " tasks in the list.");
        System.out.println("_________________________");
    }

    /**
     * Handle the deadline command, add a deadline task to the list
     * 
     * @param userInput the user input
     * @param savedTasks the saved tasks
     */
    private static void handleDeadlineCommand(String userInput, ArrayList<Task> savedTasks) throws ChatZHException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            throw new ChatZHException("The description of a deadline cannot be empty.");
        }

        String rest = parts[1].trim();
        if (rest.isEmpty()) {
            throw new ChatZHException("The description of a deadline cannot be empty.");
        }

        String[] parts2 = rest.split(" /by ", 2);
        if (parts2.length < 2) {
            throw new ChatZHException("Please specify a deadline using /by keyword.");
        }

        String description = parts2[0].trim();
        String deadline = parts2[1].trim();
        
        if (description.isEmpty() || deadline.isEmpty()) {
            throw new ChatZHException("Both description and deadline cannot be empty.");
        }

        Deadlines ddl = new Deadlines(description, deadline, false);
        savedTasks.add(ddl);
        
        System.out.println("_________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(ddl.getStatusText());
        System.out.println("Now you have " + savedTasks.size() + " tasks in the list.");
        System.out.println("_________________________");
    }

    /**
     * Handle the event command, add an event task to the list
     * 
     * @param userInput the user input
     * @param savedTasks the saved tasks
     */
    private static void handleEventCommand(String userInput, ArrayList<Task> savedTasks) throws ChatZHException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            throw new ChatZHException("The description of an event cannot be empty.");
        }

        String rest = parts[1].trim();
        if (rest.isEmpty()) {
            throw new ChatZHException("The description of an event cannot be empty.");
        }

        String[] parts2 = rest.split(" /from ", 2);
        if (parts2.length < 2) {
            throw new ChatZHException("Please specify start time using /from keyword.");
        }

        String description = parts2[0].trim();
        String times = parts2[1].trim();
        
        String[] timeParts = times.split(" /to ", 2);
        if (timeParts.length < 2) {
            throw new ChatZHException("Please specify end time using /to keyword.");
        }

        String startTime = timeParts[0].trim();
        String endTime = timeParts[1].trim();
        
        if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new ChatZHException("Description, start time, and end time cannot be empty.");
        }

        Events event = new Events(description, startTime, endTime, false);
        savedTasks.add(event);
        
        System.out.println("_________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(event.getStatusText());
        System.out.println("Now you have " + savedTasks.size() + " tasks in the list.");
        System.out.println("_________________________");
    }

    /**
     * Handle the delete command, delete the task from the list
     * 
     * @param userInput the user input
     * @param savedTasks the saved tasks
     */
    private static void handleDeleteCommand(String userInput, ArrayList<Task> savedTasks) {
        String[] parts = userInput.split(" ", 2);

        int idx = Character.getNumericValue(userInput.charAt(7)) - 1;

        Task deletedTask = savedTasks.remove(idx);

        System.out.println("_________________________");
        System.out.println("Roger. I've removed this task:");
        System.out.println(deletedTask.getStatusText());
        System.out.println("Now you have " + savedTasks.size() + " tasks in the list.");
        System.out.println("_________________________");
    }

    /**
     * Handle the find command, find the task from the list
     * 
     * @param userInput the user input
     * @param savedTasks the saved tasks
     */
    private static void handleFindCommand(String userInput, ArrayList<Task> savedTasks) {
        String[] parts = userInput.split(" ", 2);
        String keyword = parts[1].trim();
        
        System.out.println("_________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < savedTasks.size(); i++) {
            Task task = savedTasks.get(i);
            if (task.getDescription().contains(keyword)) {
                System.out.println(String.valueOf(i + 1) + "." + task.getStatusText());
            }
        }
        System.out.println("_________________________");
    }

}