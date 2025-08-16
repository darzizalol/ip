public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String getStatusText() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }

}