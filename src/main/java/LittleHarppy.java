import java.util.ArrayList;
import java.util.Scanner;

abstract class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    void markAsDone() {
        this.isDone = true;
    }

    void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public abstract String toString();
}

class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + description;
    }
}

class Deadline extends Task {
    private final String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + description + " (by: " + by + ")";
    }
}

class Event extends Task {
    private final String from;
    private final String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
    }
}

public class LittleHarppy {
    private static final String CHATBOT_NAME = "LittleHarppy";
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        displayWelcomeMessage();

        boolean chatbotIsRunning = true;

        while (chatbotIsRunning) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                displayExitMessage();
                chatbotIsRunning = false;
            } else {
                processUserInput(userInput);
            }
        }
        scanner.close();
    }

    private static void displayWelcomeMessage() {
        printSeparator();
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("  /^_^/");
        System.out.println(" ( o.o ) ");
        System.out.println("  > ~ <");
        System.out.println("\nYou can add tasks typing 'todo ...', 'deadline ...', or 'event ...'.");
        System.out.println("For example: 'todo Buy groceries'\n");
        System.out.println("To check your tasks, type 'list'.");
        System.out.println("To mark a task as done, type 'mark' followed by its number.");
        System.out.println("To unmark a task, type 'unmark' followed by its number.");
        System.out.println("Type 'bye' to exit.");
        printSeparator();
    }

    private static void displayExitMessage() {
        printSeparator();
        System.out.println("Bye! Hope to see you again soon.");
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    private static void processUserInput(String input) {
        if (input.equalsIgnoreCase("list")) {
            listTasks();
        } else if (input.startsWith("mark ")) {
            modifyTaskStatus(input, true);
        } else if (input.startsWith("unmark ")) {
            modifyTaskStatus(input, false);
        } else if (input.startsWith("remove ")) {
            removeTask(input);
        } else if (input.startsWith("todo ")) {
            addTodoTask(input);
        } else if (input.startsWith("deadline ")) {
            addDeadlineTask(input);
        } else if (input.startsWith("event ")) {
            addEventTask(input);
        } else {
            System.out.println("Invalid command! Use 'todo', 'deadline', or 'event'.");
        }
    }

    private static void listTasks() {
        printSeparator();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printSeparator();
    }

    private static void modifyTaskStatus(String input, boolean markAsDone) {
        final int taskIndexOffset = 1;
        final int expectedInputParts = 2;
        final int elementNumber = 1;
        final String regex = " ";
        try {
            String[] inputParts = input.split(regex);
            if (inputParts.length < expectedInputParts) {
                throw new IllegalArgumentException("Missing task number.");
            }

            int taskIndex = Integer.parseInt(inputParts[elementNumber]) - taskIndexOffset;
            Task selectedTask = tasks.get(taskIndex);

            if (markAsDone) {
                selectedTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                selectedTask.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  " + selectedTask);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task number out of range. Please enter a valid task number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeTask(String input) {
        final String regex = " ";
        final int elementNumber = 1;
        final int countBackIndex = 1;
        try {
            int taskNumber = Integer.parseInt(input.split(regex)[elementNumber]) - countBackIndex;

            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                System.out.println("Invalid task number.");
                return;
            }

            Task removedTask = tasks.remove(taskNumber);
            System.out.println("Removed task: " + removedTask);
        } catch (NumberFormatException e) {
            // Handle case where input is not a valid number
            System.out.println("Invalid task number format.");
        } catch (IndexOutOfBoundsException e) {
            // Handle case where the index is out of range
            System.out.println("Task number out of range.");
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.out.println("An error occurred while removing the task.");
        }
    }

    private static void addTodoTask(String input) {
        final int TODO_DESCRIPTION_START_INDEX = 5;
        try {
            String taskDescription = input.substring(TODO_DESCRIPTION_START_INDEX).trim();
            if (input.length() <= TODO_DESCRIPTION_START_INDEX) {
                System.out.println("Todo description cannot be empty!");
                return;
            }

            if (taskDescription.isEmpty()) {
                System.out.println("Todo description cannot be empty!");
                return;
            }

            Task newTodoTask = new Todo(taskDescription);
            tasks.add(newTodoTask);
            printTaskAdded(newTodoTask);

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Invalid input format. Please provide a valid description.");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the task: " + e.getMessage());
        }
    }

    private static void addDeadlineTask(String input) {
        final int DEADLINE_DESCRIPTION_START_INDEX = 9;
        final String regex = " /by ";
        final int limit = 2;
        final int descriptionPartIndex = 0;
        final int deadlineTimePartIndex = 1;
        final String DEADLINE_FORMAT_ERROR = "Invalid format. Use: deadline <description> /by <time>";
        try {
            String[] parts = input.substring(DEADLINE_DESCRIPTION_START_INDEX).split(regex, limit);

            if (parts.length < limit) {
                System.out.println(DEADLINE_FORMAT_ERROR);
                return;
            }

            String description = parts[descriptionPartIndex].trim();
            String deadlineTime = parts[deadlineTimePartIndex].trim();

            if (description.isEmpty() || deadlineTime.isEmpty()) {
                System.out.println(DEADLINE_FORMAT_ERROR);
                return;
            }

            Task newTask = new Deadline(description, deadlineTime);
            tasks.add(newTask);
            printTaskAdded(newTask);

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Input string is too short. Please provide a valid description and time.");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the deadline task: " + e.getMessage());
        }
    }

    private static void addEventTask(String input) {
        final int EVENT_DESCRIPTION_START_INDEX = 6;
        final String EVENT_FORMAT_ERROR = "Invalid format. Use: event <description> /from <time> /to <time>";
        final String regex = " /from | /to ";
        final int partLimit = 3;
        final int firstPartIndex = 0;
        final int secondPartIndex = 1;
        final int thirdPartIndex = 2;
        try {
            String[] parts = input.substring(EVENT_DESCRIPTION_START_INDEX).split(regex, partLimit);

            if (parts.length < partLimit) {
                System.out.println(EVENT_FORMAT_ERROR);
                return;
            }

            // Trim the description, from time, and to time, then create a new Event task
            String description = parts[firstPartIndex].trim();
            String fromTime = parts[secondPartIndex].trim();
            String toTime = parts[thirdPartIndex].trim();

            if (description.isEmpty() || fromTime.isEmpty() || toTime.isEmpty()) {
                System.out.println(EVENT_FORMAT_ERROR);
                return;
            }

            Task newTask = new Event(description, fromTime, toTime);
            tasks.add(newTask);
            printTaskAdded(newTask);

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Input string is too short. Please provide a valid description and time range.");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the event task: " + e.getMessage());
        }
    }

    private static void printTaskAdded(Task taskAdded) {
        System.out.println("Got it. I've added this task: " + taskAdded);
    }
}
