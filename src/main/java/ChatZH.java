import java.util.Scanner;
import java.util.ArrayList;

public class ChatZH {

    private static final String DATA_FILE_PATH = "./data/tasks.txt";
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String initMessage = "_________________________\n" +
                "Hello! I'm ChatZH\n" +
                "What can I do for you?\n" +
                "_________________________\n";

        System.out.println(initMessage);

        ArrayList<Task> savedTasks = new ArrayList<>();

        // Load tasks into savedTasks list
        FileLoading fileLoading = new FileLoading(DATA_FILE_PATH);
        fileLoading.loadTasksFromFile(savedTasks);

        // Detect user inputs
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            
            if (userInput.equals("bye")) {

                // Save Tasks to DATA_FILE_PATH
                FileSaving fileSaver = new FileSaving(DATA_FILE_PATH);
                fileSaver.saveTasksToFile(savedTasks);

                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                
                break;
            }

            HandleCommand.handleUserCommand(userInput, savedTasks);
            
        }
        
    }
}
