public class Events extends Task {

    private String startTime;
    private String endTime;

    public Events(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getStatusText() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description
                + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

}