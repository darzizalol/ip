import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Events(String description, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = parseDateTime(startTime);
        this.endTime = parseDateTime(endTime);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),  // 02/12/2019 1800
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),    // 2/12/2019 1800
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),       // 02/12/2019
            DateTimeFormatter.ofPattern("d/M/yyyy"),         // 2/12/2019
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"), // 02/12/2019 18:00
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm")    // 2/12/2019 18:00
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                if (dateTime.contains(":")) {
                    return LocalDateTime.parse(dateTime, formatter);
                } else if (dateTime.length() <= 10) {
                    LocalDate date = LocalDate.parse(dateTime, formatter);
                    return date.atStartOfDay();
                } else {
                    return LocalDateTime.parse(dateTime, formatter);
                }
            } catch (DateTimeParseException e) {
                continue;
            }
        }
    
        throw new IllegalArgumentException("Unable to parse date/time: " + dateTime + 
            ". Supported formats: dd/MM/yyyy HHmm, d/M/yyyy HHmm, dd/MM/yyyy, d/M/yyyy");
    }

    @Override
    public String getStatusText() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description
                + " (from: " + this.getStartTime() + " to: " + this.getEndTime() + ")";
    }

    public String getStartTime() {
        return this.startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public String getEndTime() {
        return this.endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isTaskDone() ? "1" : "0") + " | " + description + " | " + this.getStartTime() + " | " + this.getEndTime();
    }
}