package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;

/**
 * This class is responsible for loading tasks from a file.
 */
public class TasksLoader {

    private static final String todoTaskType = "T";
    private static final String deadlineTaskType = "D";
    private static final String eventTaskType = "E";
    private static final String taskSeparator = " \\| ";
    private static final String doneStatus = "1";

    /**
     * Loads tasks from a file and converts them into Task objects.
     */
    public static ArrayList<Task> loadTasks(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    /**
     * Parses a line from the file and returns the corresponding Task object.
     */
    private static Task parseTaskFromLine(String line) {
        String[] taskData = line.split(taskSeparator);

        if (taskData.length < 3) {
            System.out.println("Invalid task format: " + line);
            return null;
        }

        Task task = null;

        switch (taskData[0]) {
        case todoTaskType:
            task = createTodoTask(taskData);
            break;
        case deadlineTaskType:
            task = createDeadlineTask(taskData);
            break;
        case eventTaskType:
            task = createEventTask(taskData);
            break;
        default:
            System.out.println("Unknown task type: " + taskData[0]);
            break;
        }

        if (task != null) {
            markTaskAsDone(taskData[1], task);
        }

        return task;
    }

    /**
     * Creates a Todo task from the task data.
     */
    private static Task createTodoTask(String[] taskData) {
        if (taskData.length == 3) {
            return new Todo(taskData[2]);
        }
        return null;
    }

    /**
     * Creates a Deadline task from the task data.
     */
    private static Task createDeadlineTask(String[] taskData) {
        if (taskData.length == 4) {
            return new Deadline(taskData[2], taskData[3]);
        }
        return null;
    }

    /**
     * Creates an Event task from the task data.
     */
    private static Task createEventTask(String[] taskData) {
        if (taskData.length == 5) {
            return new Event(taskData[2], taskData[3], taskData[4]);
        }
        return null;
    }

    /**
     * Marks the task as done based on the status from the file.
     */
    private static void markTaskAsDone(String status, Task task) {
        if (doneStatus.equals(status)) {
            task.markAsDone();
        }
    }
}

