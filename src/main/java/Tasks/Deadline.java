package Tasks;

/**
 * Represents a Deadline task, which includes a description and a deadline time.
 * A Deadline task is a type of task with a specific deadline by which it needs to be completed.
 */
public class Deadline extends Task {
    private static final String taskType = "[D]";
    private static final String deadlinePrefix = " (by: ";
    private static final String closeBracket = ")";
    private final String deadlineTime;

    /**
     * Constructs a Deadline task with the given description and deadline time.
     */
    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns the deadline time of this task.
     */
    public String getDeadlineTime() {
        return deadlineTime;
    }

    /**
     * Returns a string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return taskType + super.toString() + deadlinePrefix + deadlineTime + closeBracket;
    }
}
