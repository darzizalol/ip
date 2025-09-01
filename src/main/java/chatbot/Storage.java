package chatbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage is the class that handles the storage of the tasks.
 * @author Fang ZhengHao
 * @version 1.0
 * @since 1.0
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for Storage
     *
     * @param filePath the path to the data file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Save the tasks to the storage file
     *
     * @param tasks the tasks to save
     * @return the saved tasks
     */
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

    /**
     * Load the tasks from the storage file
     *
     * @return the loaded tasks
     */
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
                        task = new ToDo(parts[2], true);
                    } else {
                        task = new ToDo(parts[2], false);
                    }
                    tasks.add(task);
                } else if (parts[0].equals("D")) {
                    if (parts[1].equals("1")) {
                        task = new Deadline(parts[2], parts[3], true);
                    } else {
                        task = new Deadline(parts[2], parts[3], false);
                    }
                    tasks.add(task);
                } else if (parts[0].equals("E")) {
                    if (parts[1].equals("1")) {
                        task = new Event(parts[2], parts[3], parts[4], true);
                    } else {
                        task = new Event(parts[2], parts[3], parts[4], false);
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
