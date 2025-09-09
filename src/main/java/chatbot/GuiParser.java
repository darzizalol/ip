package chatbot;
import java.util.ArrayList;

/**
 * Parser is the class that handles the user command.
 * @author Fang ZhengHao
 * @version 1.0
 * @since 1.0
 */
public class GuiParser {

    /**
     * Handle the user command, log any errors, and determine the command type,
     * and triggers the respective handler methods
     *
     * @param userInput the user input
     * @param savedTasks the saved tasks
     * @return the response to the user
     */
    public static String handleGuiUserCommand(String userInput, ArrayList<Task> savedTasks) {
        try {
            if (userInput.equals("list")) {
                return handleGuiListCommand(savedTasks);
            } else if (userInput.startsWith("mark")) {
                return handleGuiMarkCommand(userInput, savedTasks, true);
            } else if (userInput.startsWith("unmark")) {
                return handleGuiMarkCommand(userInput, savedTasks, false);
            } else if (userInput.startsWith("todo")) {
                return handleGuiTodoCommand(userInput, savedTasks);
            } else if (userInput.startsWith("deadline")) {
                return handleGuiDeadlineCommand(userInput, savedTasks);
            } else if (userInput.startsWith("event")) {
                return handleGuiEventCommand(userInput, savedTasks);
            } else if (userInput.startsWith("delete")) {
                return handleGuiDeleteCommand(userInput, savedTasks);
            } else if (userInput.startsWith("find")) {
                return handleGuiFindCommand(userInput, savedTasks);
            } else if (userInput.startsWith("update")) {
                return handleGuiUpdateNameCommand(userInput, savedTasks);
            } else if (userInput.startsWith("edit")) {
                return handleGuiUpdateTimeCommand(userInput, savedTasks);
            } else {
                throw new ChatZHException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (ChatZHException e) {
            return "_________________________\n"
                    + "OOPS!!! " + e.getMessage() + "\n"
                    + "_________________________";
        } catch (Exception e) {
            return "_________________________\n"
                    + "OOPS!!! An unexpected error occurred: " + e.getMessage() + "\n"
                    + "_________________________";
        }
    }

    /**
     * Handle the list command, print all the saved tasks in the list
     *
     * @param savedTasks the saved tasks
     * @return the response to the user
     */
    private static String handleGuiListCommand(ArrayList<Task> savedTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("_________________________\n");
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < savedTasks.size(); i++) {
            Task task = savedTasks.get(i);
            sb.append(i + 1).append(".").append(task.getStatusText()).append("\n");
        }
        sb.append("_________________________");

        return sb.toString();
    }

    /**
     * Handle the mark command, mark the task as done or not done
     *
     * @param userInput the user input
     * @param savedTasks the saved tasks
     * @param markAsDone whether to mark the task as done
     * @return the response to the user
     * @throws ChatZHException
     */
    private static String handleGuiMarkCommand(String userInput, ArrayList<Task> savedTasks, boolean markAsDone)
            throws ChatZHException {
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

        StringBuilder sb = new StringBuilder();
        sb.append("_________________________\n");

        if (markAsDone) {
            sb.append("Nice! I've marked this task as done:\n");
        } else {
            sb.append("OK, I've marked this task as not done yet:\n");
        }

        sb.append(task.getStatusText()).append("\n");
        sb.append("_________________________");

        return sb.toString();
    }

    /**
     * Handle the todo command, add a todo task to the list
     *
     * @param userInput the user input
     * @param savedTasks the saved tasks
     * @return the response to the user
     * @throws ChatZHException
     */
    private static String handleGuiTodoCommand(String userInput, ArrayList<Task> savedTasks) throws ChatZHException {
        String[] parts = userInput.split(" ", 2);
        String description = parts.length > 1 ? parts[1].trim() : "";
        if (description.isEmpty()) {
            throw new ChatZHException("The description of a todo cannot be empty.");
        }

        ToDo todo = new ToDo(description, false);
        savedTasks.add(todo);

        StringBuilder sb = new StringBuilder();
        sb.append("_________________________\n");
        sb.append("Got it. I've added this task:\n");
        sb.append(todo.getStatusText()).append("\n");
        sb.append("Now you have ").append(savedTasks.size()).append(" tasks in the list.\n");
        sb.append("_________________________");

        return sb.toString();
    }

    /**
     * Handle the deadline command, add a deadline task to the list
     *
     * @param userInput the user input
     * @param savedTasks the saved tasks
     * @return the response to the user
     * @throws ChatZHException
     */
    private static String handleGuiDeadlineCommand(String userInput, ArrayList<Task> savedTasks)
            throws ChatZHException {
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

        Deadline ddl = new Deadline(description, deadline, false);
        savedTasks.add(ddl);

        StringBuilder sb = new StringBuilder();
        sb.append("_________________________\n");
        sb.append("Got it. I've added this task:\n");
        sb.append(ddl.getStatusText()).append("\n");
        sb.append("Now you have ").append(savedTasks.size()).append(" tasks in the list.\n");
        sb.append("_________________________");

        return sb.toString();
    }


    /**
     * Handle the event command, add an event task to the list
     *
     * @param userInput the user input
     * @param savedTasks the saved tasks
     * @return the response to the user
     * @throws ChatZHException
     */
    private static String handleGuiEventCommand(String userInput, ArrayList<Task> savedTasks) throws ChatZHException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            throw new ChatZHException("The description of an event cannot be empty.");
        }

        String rest = parts[1].trim();
        if (rest.isEmpty()) {
            throw new ChatZHException("The description of an event cannot be empty.");
        }

        assert !rest.isEmpty();

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

        Event event = new Event(description, startTime, endTime, false);
        savedTasks.add(event);

        StringBuilder sb = new StringBuilder();
        sb.append("_________________________\n");
        sb.append("Got it. I've added this task:\n");
        sb.append(event.getStatusText()).append("\n");
        sb.append("Now you have ").append(savedTasks.size()).append(" tasks in the list.\n");
        sb.append("_________________________");

        return sb.toString();
    }

    /**
     * Handle the delete command, delete the task from the list
     *
     * @param userInput the user input
     * @param savedTasks the saved tasks
     * @return the response to the user
     * @throws ChatZHException
     */
    private static String handleGuiDeleteCommand(String userInput, ArrayList<Task> savedTasks) throws ChatZHException {
        String[] parts = userInput.split(" ", 2);

        if (parts.length < 2) {
            throw new ChatZHException("Please specify a task number to delete!");
        }

        int idx = Character.getNumericValue(userInput.charAt(7)) - 1;

        if (idx < 0 || idx >= savedTasks.size()) {
            throw new ChatZHException("Invalid task number! Please use a number between 1 and " + savedTasks.size());
        }

        Task deletedTask = savedTasks.remove(idx);

        StringBuilder sb = new StringBuilder();
        sb.append("_________________________\n");
        sb.append("Roger. I've removed this task:\n");
        sb.append(deletedTask.getStatusText()).append("\n");
        sb.append("Now you have ").append(savedTasks.size()).append(" tasks in the list.\n");
        sb.append("_________________________");

        return sb.toString();
    }

    /**
     * Handle the find command, find the task from the list
     *
     * @param userInput the user input
     * @param savedTasks the saved tasks
     * @return the response to the user
     * @throws ChatZHException
     */
    private static String handleGuiFindCommand(String userInput, ArrayList<Task> savedTasks) throws ChatZHException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChatZHException("Please provide a keyword to search for.");
        }

        String keyword = parts[1].trim();

        StringBuilder sb = new StringBuilder();
        sb.append("_________________________\n");
        sb.append("Here are the matching tasks in your list:\n");

        boolean found = false;
        for (int i = 0; i < savedTasks.size(); i++) {
            Task task = savedTasks.get(i);
            if (task.getDescription().contains(keyword)) {
                sb.append(i + 1).append(".").append(task.getStatusText()).append("\n");
                found = true;
            }
        }

        if (!found) {
            sb.append("No tasks match your keyword.\n");
        }

        sb.append("_________________________");

        return sb.toString();
    }

    /**
     * Handle the update command, update task description
     * @param userInput
     * @param savedTasks
     * @return the response to the user
     * @throws ChatZHException
     */
    private static String handleGuiUpdateNameCommand(String userInput, ArrayList<Task> savedTasks) throws ChatZHException {
        String[] parts = userInput.split(" ", 3);
        if (savedTasks.isEmpty()) {
            throw new ChatZHException("There are no tasks to update!");
        }

        if (parts.length < 2) {
            throw new ChatZHException("Please specify a task number to update!");
        } else if (parts.length < 3) {
            throw new ChatZHException("Please give the task a new description!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("_________________________\n");
        Task taskToBeUpdated = savedTasks.get(Character.getNumericValue(parts[1].charAt(0)) - 1);
        sb.append("I've updated Task " + parts[1] + " from " + taskToBeUpdated.getDescription() + " to " + parts[2] + "\n");
        sb.append("_________________________");
        taskToBeUpdated.setDescription(parts[2]);

        return sb.toString();
    }

    /**
     * Handle the edit command, update task time
     * @param userInput
     * @param savedTasks
     * @return the response to the user
     * @throws ChatZHException
     */
    private static String handleGuiUpdateTimeCommand(String userInput, ArrayList<Task> savedTasks) throws ChatZHException {
        // Sample user input: edit 1 start 2025-09-09 10:00
        // Sample user input: edit 1 end 2025-09-09 10:00
        // Sample user input: edit 2 2025-09-09 10:00
        
        String[] parts = userInput.split(" ", 4);
        if (savedTasks.isEmpty()) {
            throw new ChatZHException("There are no tasks to update!");
        }

        if (parts.length < 2) {
            throw new ChatZHException("Please specify a task number to update!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("_________________________\n");
        Task taskToBeUpdated = savedTasks.get(Character.getNumericValue(parts[1].charAt(0)) - 1);
        sb.append("I've updated Task " + parts[1] + " from " + taskToBeUpdated.getStatusText() + " to ");

        if (taskToBeUpdated.getType().equals("T")) {
            taskToBeUpdated.setTime(parts[2]);
        } else if (taskToBeUpdated.getType().equals("D")) {
            taskToBeUpdated.setTime(parts[2]);
        } else if (taskToBeUpdated.getType().equals("E")) {
            Event event = (Event) taskToBeUpdated;
            if (parts[2].equals("start")) {
                event.setStartTime(parts[3]);
            } else if (parts[2].equals("end")) {
                event.setEndTime(parts[3]);
            } else {
                throw new ChatZHException("Please specify a start or end time to update!");
            }
        } 

        sb.append(taskToBeUpdated.getStatusText() + "\n");
        sb.append("_________________________");
        return sb.toString();
    }
}
