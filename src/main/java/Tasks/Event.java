package Tasks;

/**
 * Represents an Event task, which includes a description, a start time, and an end time.
 */
public class Event extends Task {
    private final String fromTime;
    private final String toTime;
    private static final String taskType = "[E]";
    private static final String eventPrefix = " (from: ";
    private static final String eventSuffix = " to: ";
    private static final String closeBracket = ")";

    /**
     * Constructs an Event task with the given description, start time, and end time.
     */
    public Event(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Returns the start time of this event.
     */
    public String getFromTime() {
        return fromTime;
    }

    /**
     * Returns the end time of this event.
     */
    public String getToTime() {
        return toTime;
    }

    /**
     * Returns a string representation of the Event task.
     */
    @Override
    public String toString() {
        return taskType + super.toString() + eventPrefix + fromTime + eventSuffix + toTime + closeBracket;
    }
}
