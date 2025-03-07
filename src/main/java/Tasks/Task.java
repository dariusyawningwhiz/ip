package Tasks;

/**
 * Represents a generic Task, which includes a description and a completion status.
 * All specific task types like Todo, Deadline, and Event inherit from this class.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;
    private static final String doneStatus = "[X] ";
    private static final String undoneStatus = "[ ] ";
    /**
     * Constructs a Task with the given description. Initially, the task is not done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the Task.
     * The format includes the task status (done or not) and description.
     */
    @Override
    public String toString() {
        return (isDone ? doneStatus : undoneStatus) + description;
    }
}
