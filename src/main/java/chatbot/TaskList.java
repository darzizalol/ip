package chatbot;
import java.util.ArrayList;

/**
 * TaskList is the class that handles the list of tasks.
 * @author Fang ZhengHao
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("checkstyle:Regexp")
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList
     *
     * @param tasks the tasks to be added to the list
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    /**
     * Add a task to the list
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Remove a task from the list
     *
     * @param task the task to be removed
     */
    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Get all the tasks in the list
     *
     * @return all the tasks in the list
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }
}
