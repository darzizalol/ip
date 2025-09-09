package chatbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event is a task that has a start time and an end time.
 * @author Fang ZhengHao
 * @version 1.0
 * @since 1.0
 */
public class Event extends Task {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor for Events
     *
     * @param description the description of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param isDone whether the task is done
     */
    public Event(String description, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = parseDateTime(startTime);
        this.endTime = parseDateTime(endTime);
        assert this.startTime != null : "Start time should not be null after parsing";
        assert this.endTime != null : "End time should not be null after parsing";
    }

    /**
     * Parse the date/time string into a LocalDateTime object
     *
     * @param dateTime the date/time string to parse
     * @return the LocalDateTime object
     */
    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm")
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
        throw new IllegalArgumentException("Unable to parse date/time: " + dateTime
                + ". Supported formats: dd/MM/yyyy HHmm, d/M/yyyy HHmm, dd/MM/yyyy, d/M/yyyy");
    }

    /**
     * Get the status text of the event
     *
     * @return the status text in String
     */
    @Override
    public String getStatusText() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description
                + " (from: " + this.getStartTime() + " to: " + this.getEndTime() + ")";
    }

    /**
     * Get the start time of the event
     *
     * @return the start time in String
     */
    public String getStartTime() {
        return this.startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Get the end time of the event
     *
     * @return the end time in String
     */
    public String getEndTime() {
        return this.endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Get the file format of the event
     *
     * @return the file format in String
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isTaskDone() ? "1" : "0") + " | " + description
                + " | " + this.getStartTime() + " | " + this.getEndTime();
    }

    /**
     * Set the time of the event
     * @param time start time and end time
     */
    @Override
    public void setTime(String... time) {
        this.startTime = parseDateTime(time[0]);
        this.endTime = parseDateTime(time[1]);
    }

    /**
     * Set the start time of the event
     * @param startTime start time
     */
    public void setStartTime(String startTime) {
        this.startTime = parseDateTime(startTime);
    }
    
    /**
     * Set the end time of the event
     * @param endTime end time
     */
    public void setEndTime(String endTime) {
        this.endTime = parseDateTime(endTime);
    }

    /**
     * Get the type of the event
     * @return the type of the event
     */
    @Override
    public String getType() {
        return "E";
    }
}
