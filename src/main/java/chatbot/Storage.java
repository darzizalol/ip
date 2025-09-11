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

    private final String filePath;

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
                    task = parseTodo(parts);
                    tasks.add(task);
                } else if (parts[0].equals("D")) {
                    task = parseDeadline(parts);
                    tasks.add(task);
                } else if (parts[0].equals("E")) {
                    task = parseEvent(parts);
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

    /**
     * Parse the string array into a todo task
     * @param parts a String array of the todo description
     * @return an todo task
     */
    public ToDo parseTodo(String[] parts) {
        if (parts[1].equals("1")) {
            return new ToDo(parts[2], true);
        } else {
            return new ToDo(parts[2], false);
        }
    }

    /**
     * Parse the string array into a deadline task
     * @param parts a String array of the deadline description
     * @return a deadline task
     */
    public Deadline parseDeadline(String[] parts) {
        if (parts[1].equals("1")) {
            return new Deadline(parts[2], parts[3], true);
        } else {
            return new Deadline(parts[2], parts[3], false);
        }
    }

    /**
     * Parse the string array into an event task
     * @param parts a String array of the event description
     * @return an event task
     */
    public Event parseEvent(String[] parts) {
        if (parts[1].equals("1")) {
            return new Event(parts[2], parts[3], parts[4], true);
        } else {
            return new Event(parts[2], parts[3], parts[4], false);
        }
    }
}
