package Tasks;

/**
 * Represents an Event task, which includes a description, a start time, and an end time.
 * An Event task is a type of task that happens within a specific time range.
 */
public class Event extends Task {
    private final String fromTime;
    private final String toTime;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description the description of the event
     * @param fromTime the start time of the event
     * @param toTime the end time of the event
     */
    public Event(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Returns the start time of this event.
     *
     * @return the start time of the event
     */
    public String getFromTime() {
        return fromTime;
    }

    /**
     * Returns the end time of this event.
     *
     * @return the end time of the event
     */
    public String getToTime() {
        return toTime;
    }

    /**
     * Returns a string representation of the Event task.
     * The format includes the task type, status, description, start time, and end time.
     *
     * @return a string representation of the Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromTime + " to: " + toTime + ")";
    }
}
