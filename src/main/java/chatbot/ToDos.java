package chatbot;
public class ToDos extends Task {

    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getStatusText() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public String toFileFormat() {
        return "T | " + (this.isTaskDone() ? "1" : "0") + " | " + this.description;
    }
}