package chatbot;

/**
 * Task is the base class for all tasks.
 * @author Fang ZhengHao
 * @version 1.0
 * @since 1.0
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String time;

    /**
     * Constructor for Task
     *
     * @param description the description of the task
     * @param isDone whether the task is done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructor for Task
     *
     * @param description the description of the task
     * @param isDone whether the task is done
     * @param time the time of the task
     */
    public Task(String description, boolean isDone, String time) {
        this.description = description;
        this.isDone = isDone;
        this.time = time;
    }

    /**
     * Get the status icon of the task
     *
     * @return the status icon, 'X' for done task and ' ' for not done task
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Get the description of the task
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the status text of the task
     *
     * @return the status text of the task
     */
    public String getStatusText() {
        String statusText = "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
        if (this.time != null) {
            statusText += " (" + this.time + ")";
        }
        return statusText;
    }

    /**
     * Set the status of the task
     *
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Set the description of the task
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the file format of the task
     *
     * @return the file format of the task
     */
    public String toFileFormat() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Check if the task is done
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isTaskDone() {
        return this.isDone;
    }

    /**
     * Set the time of the task
     * @param time
     */
    public void setTime(String... time) {
        this.time = time[0];
    }

    /**
     * Get the type of the task
     * @return the type of the task
     */
    public String getType() {
        return "T";
    }
}
