package chatbot;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    private final String initMessage = "_________________________\n" +
                "Hello! I'm ChatZH\n" +
                "What can I do for you?\n" +
                "_________________________\n";

    /**
     * Constructor for UI
     */
    public UI () {}

    /**
     * Run the UI
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

                // Save Tasks to DATA_FILE_PATH
                storage.saveTasks(savedTasks);

                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                
                break;
            }


            Parser.handleUserCommand(userInput, savedTasks);
        
        }

    }
}
