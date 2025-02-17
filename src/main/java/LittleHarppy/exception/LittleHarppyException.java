package LittleHarppy.exception;

public class LittleHarppyException extends Exception {

    public LittleHarppyException(String message) {
        super(message);
    }


    // Exception for invalid task number input
    public static LittleHarppyException invalidTaskNumber(int maxTasks) {
        return new LittleHarppyException("Invalid task number. Total tasks: " + maxTasks);
    }

    // Exception for missing task number
    public static LittleHarppyException missingTaskNumber() {
        return new LittleHarppyException("Missing task number.");
    }

    // Exception for invalid task number format
    public static LittleHarppyException invalidTaskNumberFormat() {
        return new LittleHarppyException("Invalid task number format.");
    }

    // Exception for task removal out of range
    public static LittleHarppyException taskOutOfRange() {
        return new LittleHarppyException("Task number out of range.");
    }

    // Exception for missing description in Todo task
    public static LittleHarppyException emptyTodoDescription() {
        return new LittleHarppyException("Todo description cannot be empty!");
    }

    // Exception for incorrect deadline format
    public static LittleHarppyException incorrectDeadlineFormat() {
        return new LittleHarppyException("Invalid format. Use: deadline <description> /by <time>");
    }

    // Exception for incorrect event format
    public static LittleHarppyException incorrectEventFormat() {
        return new LittleHarppyException("Invalid format. Use: event <description> /from <time> /to <time>");
    }

    // Exception for string too short or invalid input
    public static LittleHarppyException invalidInputFormat() {
        return new LittleHarppyException("Invalid input format. Please type todo... or event... or deadline.");
    }

    // General error handling when adding tasks
    public static LittleHarppyException taskAdditionError(String message) {
        return new LittleHarppyException("An error occurred while adding the task: " + message);
    }
}