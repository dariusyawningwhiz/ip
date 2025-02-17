package Tasks;

public class Deadline extends Task {
    private String deadlineTime;

    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    public static Deadline fromFileString(String taskData) {
        final int DescriptionBeginIndex = 4;
        final int DeadlineTimeBeginIndex = 5;
        String description = taskData.substring(DescriptionBeginIndex, taskData.indexOf(" /by")).trim();
        String deadlineTime = taskData.substring(taskData.indexOf(" /by") + DeadlineTimeBeginIndex).trim();
        return new Deadline(description, deadlineTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineTime + ")";
    }
}

