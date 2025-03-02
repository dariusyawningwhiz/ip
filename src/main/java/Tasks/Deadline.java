package Tasks;

/**
 * Represents a Deadline task, which includes a description and a deadline time.
 * A Deadline task is a type of task with a specific deadline by which it needs to be completed.
 */
public class Deadline extends Task {
    private String deadlineTime;

    /**
     * Constructs a Deadline task with the given description and deadline time.
     *
     * @param description the description of the task
     * @param deadlineTime the deadline time for the task
     */
    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Converts a task data string into a Deadline object.
     * This method is used for loading a Deadline task from a file where the data is stored in a specific format.
     *
     * @param taskData the string representation of the task data
     * @return a new Deadline object created from the string data
     */
    public static Deadline fromFileString(String taskData) {
        final int DescriptionBeginIndex = 4;
        final int DeadlineTimeBeginIndex = 5;
        String description = taskData.substring(DescriptionBeginIndex, taskData.indexOf(" /by")).trim();
        String deadlineTime = taskData.substring(taskData.indexOf(" /by") + DeadlineTimeBeginIndex).trim();
        return new Deadline(description, deadlineTime);
    }

    /**
     * Returns the deadline time of this task.
     *
     * @return the deadline time of the task
     */
    public String getDeadlineTime() {
        return deadlineTime;
    }

    /**
     * Returns a string representation of the Deadline task.
     * The format includes the task type, status, description, and the deadline time.
     *
     * @return a string representation of the Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineTime + ")";
    }
}
