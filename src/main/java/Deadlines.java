public class Deadlines extends Task {

    private String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getStatusText() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.deadline + ")";
    }

}