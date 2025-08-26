package chatbot;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {

    private LocalDateTime deadline;

    /**
     * Constructor for Deadlines
     * 
     * @param description the description of the deadline
     * @param deadline the deadline of the task
     * @param isDone whether the task is done
     */
    public Deadlines(String description, String deadline, boolean isDone) {
        super(description, isDone);

        this.deadline = parseDeadline(deadline);
    }

    /**
     * Parse the deadline string into a LocalDateTime object
     * 
     * @param deadline the deadline string to parse
     * @return the LocalDateTime object
     */
    private LocalDateTime parseDeadline(String deadline) {
        DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),  // 02/12/2019 1800
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),    // 2/12/2019 1800
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),       // 02/09/2019
            DateTimeFormatter.ofPattern("d/M/yyyy"),         // 2/9/2019
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"), // 02/12/2019 18:00
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm")    // 2/12/2019 18:00
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                if (deadline.contains(":")) {
                    return LocalDateTime.parse(deadline, formatter);
                } else if (deadline.length() <= 10) {
                    LocalDate date = LocalDate.parse(deadline, formatter);
                    return date.atStartOfDay();
                } else {
                    return LocalDateTime.parse(deadline, formatter);
                }
            } catch (DateTimeParseException e) {
                continue;
            }
        }
    
        throw new IllegalArgumentException("Unable to parse deadline: " + deadline + 
            ". Supported formats: dd/MM/yyyy HHmm, d/M/yyyy HHmm, dd/MM/yyyy, d/M/yyyy");
    }

    /**
     * Get the status text of the deadline
     * 
     * @return the status text
     */
    @Override
    public String getStatusText() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.getDeadline() + ")";
    }

    /**
     * Get the deadline of the deadline
     * 
     * @return the deadline
     */
    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Get the file format of the deadline
     * 
     * @return the file format
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isTaskDone() ? "1" : "0") + " | " + this.description + " | " + this.getDeadline();
    }
}