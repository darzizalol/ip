package chatbot;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI is the class that handles the UI of the chatbot.
 * @author Fang ZhengHao
 * @version 1.0
 * @since 1.0
 */
public class UI {

    private final String initMessage = "_________________________\n"
            + "Hello! I'm ChatZH\n"
            + "What can I do for you?\n"
            + "_________________________\n";

    /**
     * Constructor for UI on Command Line Interface
     */
    @SuppressWarnings("checkstyle:MethodParamPad")
    public UI () {}

    /**
     * Run the UI on the Command Line Interface
     *
     * @param sc the scanner to read user input
     * @param storage the storage in charge of saving and loading tasks
     */
    public void run(Scanner sc, Storage storage) {
        System.out.println(this.initMessage);

        ArrayList<Task> savedTasks = storage.loadTasks();

        // Detect user inputs
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {

                storage.saveTasks(savedTasks);

                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                break;
            }

            Parser.handleUserCommand(userInput, savedTasks);
        }
    }

    /**
     * Run the UI on the GUI
     *
     * @param userInput the userInput read by ChatZH
     * @param storage the storage in charge of saving and loading tasks
     * @return the response to the user
     */
    public String run(String userInput, Storage storage) {

        ArrayList<Task> savedTasks = storage.loadTasks();
        String response = "";
        if (userInput.equals("bye")) {
            response = "Bye. Hope to see you again soon!";
        } else {
            response = GuiParser.handleGuiUserCommand(userInput, savedTasks);
        }
        storage.saveTasks(savedTasks);
        return response;
    }
}
