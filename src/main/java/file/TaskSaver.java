package file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Tasks.Task;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

/**
 * This class handles the saving of task data to a file.
 */
public class TaskSaver {

    private static final String todoTaskType = "T";
    private static final String deadlineTaskType = "D";
    private static final String eventTaskType = "E";
    private static final String taskSeparator = " | ";
    private static final String dontStatus = "1";
    private static final String notDoneStatus = "0";

    /**
     * Saves a list of tasks to a file in a specific format.
     */
    public static void saveTasks(String filePath, ArrayList<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                String taskData = formatTaskData(task);
                writer.write(taskData + System.lineSeparator());
            }
        }
    }

    /**
     * Formats the task data based on the type of task.
     */
    private static String formatTaskData(Task task) {
        String taskData = "";

        if (task instanceof Todo) {
            taskData = formatTodoTask((Todo) task);
        } else if (task instanceof Deadline) {
            taskData = formatDeadlineTask((Deadline) task);
        } else if (task instanceof Event) {
            taskData = formatEventTask((Event) task);
        }

        return taskData;
    }

    /**
     * Formats the data for a Todo task.
     */
    private static String formatTodoTask(Todo todo) {
        return todoTaskType + taskSeparator + (todo.isDone() ? dontStatus : notDoneStatus) +
                taskSeparator + todo.getDescription();
    }

    /**
     * Formats the data for a Deadline task.
     */
    private static String formatDeadlineTask(Deadline deadline) {
        return deadlineTaskType + taskSeparator + (deadline.isDone() ? dontStatus : notDoneStatus) +
                taskSeparator + deadline.getDescription() + taskSeparator + deadline.getDeadlineTime();
    }

    /**
     * Formats the data for an Event task.
     */
    private static String formatEventTask(Event event) {
        return eventTaskType + taskSeparator + (event.isDone() ? dontStatus : notDoneStatus) +
                taskSeparator + event.getDescription() + taskSeparator + event.getFromTime() +
                taskSeparator + event.getToTime();
    }
}


