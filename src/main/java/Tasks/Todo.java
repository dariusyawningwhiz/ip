package Tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public static Todo fromFileString(String taskData) {
        final int beginIndex = 4;
        String description = taskData.substring(beginIndex);
        return new Todo(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
