package Tasks;

/**
 * Represents a Todo task, which includes a description.
 * A Todo task is a simple type of task that doesn't have additional attributes like deadline or event times.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * The format includes the task type and the task status.
     *
     * @return a string representation of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

