package chatbot;

/**
 * ToDo is a task that has a description.
 * @author Fang ZhengHao
 * @version 1.0
 * @since 1.0
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDos
     *
     * @param description the description of the todo
     * @param isDone whether the todo is done
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public ToDo(String description, boolean isDone, String time) {
        super(description, isDone, time);
    }

    /**
     * Get the status text of the todo
     *
     * @return the status text of the todo
     */
    @Override
    public String getStatusText() {
        String statusText = "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
        if (this.time != null) {
            statusText += " (" + this.time + ")";
        }
        return statusText;
    }

    /**
     * Get the file format of the todo
     *
     * @return the file format of the todo
     */
    @Override
    public String toFileFormat() {
        return "T | " + (this.isTaskDone() ? "1" : "0") + " | " + this.description;
    }
}
