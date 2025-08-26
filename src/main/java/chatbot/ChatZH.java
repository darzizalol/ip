package chatbot;
import java.util.Scanner;

public class ChatZH {

    private static final String DATA_FILE_PATH = "./data/tasks.txt";
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    /**
     * Constructor for ChatZH
     * 
     * @param filePath the path to the data file
     */
    public ChatZH(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.loadTasks());
        this.ui = new UI();
    }

    /**
     * Run the chatbot
     * 
     * @return void
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        this.ui.run(sc, this.storage);
    }

    /**
     * Main method to run the chatbot
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        ChatZH chatZH = new ChatZH(DATA_FILE_PATH);
        chatZH.run();
    }
}
