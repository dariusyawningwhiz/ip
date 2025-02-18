package Tasks;

public class Event extends Task {
    private String fromTime;
    private String toTime;

    public Event(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public String getFromTime() {  // Add this method
        return fromTime;
    }

    public String getToTime() {  // Add this method
        return toTime;
    }

    public static Event fromFileString(String taskData) {
        String description = taskData.substring(6, taskData.indexOf(" /from")).trim();
        String fromTime = taskData.substring(taskData.indexOf(" /from") + 6, taskData.indexOf(" /to")).trim();
        String toTime = taskData.substring(taskData.indexOf(" /to") + 4).trim();
        return new Event(description, fromTime, toTime);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromTime + " to: " + toTime + ")";
    }
}

