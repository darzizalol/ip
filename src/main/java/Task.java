public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusText() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void setStatus(boolean status) {
        this.isDone = status;
    }

    public String toFileFormat() {
        return "T | " + (this.isDone ? "1": "0") + " | " + this.description;
    }

    public boolean isTaskDone() {
        return this.isDone;
    }
}
