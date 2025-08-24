public class Events extends Task {

    private String startTime;
    private String endTime;

    public Events(String description, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getStatusText() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description
                + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isTaskDone() ? "1" : "0") + " | " + description + " | " + startTime + " | " + endTime;
    }
}