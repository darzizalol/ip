import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class FileLoading {

    private String DATA_FILE_PATH;

    public FileLoading(String DataFilePath) {
        this.DATA_FILE_PATH = DataFilePath;
    }

    public void loadTasksFromFile(ArrayList<Task> savedTasks) {

        File file = new File(DATA_FILE_PATH);

        if (!file.exists()) {
            try {
                // Create parent directories if they don't exist
                file.getParentFile().mkdirs();
                // Create the file
                boolean hasCreatedFile = file.createNewFile();
                if (hasCreatedFile) {
                    System.out.println("Created a brand new list!");
                    System.out.println("_________________________");
                }
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String[] parts = s.nextLine().split("\\|");
    
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
    
                Task task;
    
                if (parts[0].equals("T")) {
                    if (parts[1].equals("1")) {
                        task = new ToDos(parts[2], true);
                    } else {
                        task = new ToDos(parts[2], false);
                    }
                    savedTasks.add(task);
                } else if (parts[0].equals("D")) {
                    if (parts[1].equals("1")) {
                        task = new Deadlines(parts[2], parts[3], true);
                    } else {
                        task = new Deadlines(parts[2], parts[3], false);
                    }
                    savedTasks.add(task);
                } else if (parts[0].equals("E")) {
                    if (parts[1].equals("1")) {
                        task = new Events(parts[2], parts[3], parts[4], true);
                    } else {
                        task = new Events(parts[2], parts[3], parts[4], false);
                    }
                    savedTasks.add(task);
                }    
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("There are no tasks in the list.");
            System.out.println("_________________________");
        }
    }

}