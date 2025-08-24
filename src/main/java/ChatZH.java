import java.util.Scanner;
import java.util.ArrayList;

public class ChatZH {

    private static final String DATA_FILE_PATH = "./data/tasks.txt";
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public ChatZH(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.loadTasks());
        this.ui = new UI();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        this.ui.run(sc, this.storage);
    }


    public static void main(String[] args) {

        ChatZH chatZH = new ChatZH(DATA_FILE_PATH);
        chatZH.run();
    }
}
