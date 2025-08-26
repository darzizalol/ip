package chatbot;
public class Task {
    protected String description;
    protected boolean isDone;

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
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
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
     * Get the file format of the task
     * 
     * @return the file format of the task
     */
    public String toFileFormat() {
        return "T | " + (this.isDone ? "1": "0") + " | " + this.description;
    }

    /**
     * Check if the task is done
     * 
     * @return true if the task is done, false otherwise
     */
    public boolean isTaskDone() {
        return this.isDone;
    }
}
