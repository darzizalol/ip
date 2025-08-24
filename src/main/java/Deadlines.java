public class Deadlines extends Task {

    private String deadline;

    public Deadlines(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getStatusText() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.deadline + ")";
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isTaskDone() ? "1" : "0") + " | " + description + " | " + deadline;
    }
}