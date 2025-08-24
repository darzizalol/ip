package chatbot;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);

            for (int i = 0; i < tasks.size(); i++) {
                String textToWrite = tasks.get(i).toFileFormat() + System.lineSeparator();
                fw.write(textToWrite);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("There is an IOException, fix it!");
        }
        return tasks;
    }

    public ArrayList<Task> loadTasks() {
        File file = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();

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
                    tasks.add(task);
                } else if (parts[0].equals("D")) {
                    if (parts[1].equals("1")) {
                        task = new Deadlines(parts[2], parts[3], true);
                    } else {
                        task = new Deadlines(parts[2], parts[3], false);
                    }
                    tasks.add(task);
                } else if (parts[0].equals("E")) {
                    if (parts[1].equals("1")) {
                        task = new Events(parts[2], parts[3], parts[4], true);
                    } else {
                        task = new Events(parts[2], parts[3], parts[4], false);
                    }
                    tasks.add(task);
                }    
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("There are no tasks in the list.");
            System.out.println("_________________________");
        }
        return tasks;
    }
}
