import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;

public class FileSaving {

    private String DATA_FILE_PATH;

    public FileSaving(String DataFilePath) {
        this.DATA_FILE_PATH = DataFilePath;
    }

    public void saveTasksToFile(ArrayList<Task> savedTasks) {
        
        try {
            FileWriter fw = new FileWriter(DATA_FILE_PATH);

            for (int i = 0; i < savedTasks.size(); i++) {
                String textToWrite = savedTasks.get(i).toFileFormat() + System.lineSeparator();
                fw.write(textToWrite);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("There is an IOException, fix it!");
        }
        
        
    }

}
