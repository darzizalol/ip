package chatbot;
import java.util.Scanner;

/**
 * ChatZH is a task management chatbot application that allows users to manage their tasks,
 * including todos, deadlines, and events. The application provides an interactive command-line
 * interface for adding, listing, marking, and deleting tasks.
 *
 * <p>The application automatically persists task data to a file and loads it when restarted,
 * ensuring that user tasks are preserved between sessions.
 *
 * @author Fang ZhengHao
 * @version 1.0
 * @since 1.0
 */
public class ChatZh {

    private static final String DATA_FILE_PATH = "./data/tasks.txt";
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    /**
     * Constructor for ChatZH
     * @param filePath the path to the data file
     */
    public ChatZh(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.loadTasks());
        this.ui = new UI();
    }

    /**
     * Run the chatbot
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        this.ui.run(sc, this.storage);
    }

    /**
     * Main method to run the chatbot
     * @param args command line arguments
     */
    public static void main(String[] args) {

        ChatZh chatZh = new ChatZh(DATA_FILE_PATH);
        chatZh.run();
    }

    /**
     * Generates a response for the GUI.
     */
    public String getResponse(String userInput) {
        return this.ui.run(userInput, this.storage);
    }
}
