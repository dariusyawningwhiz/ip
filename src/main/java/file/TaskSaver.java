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
 * It writes task information in a specific format so that it can later be loaded correctly.
 */
public class TaskSaver {
    /**
     * Saves a list of tasks to a file in a specific format.
     * Each task is written as a string representing its type, completion status, and other relevant details.
     *
     * @param filePath the file path where the tasks should be saved
     * @param tasks the list of tasks to save
     * @throws IOException if there is an error while writing to the file
     */
    public static void saveTasks(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        // Loop through each task and write its data in a specific format
        for (Task task : tasks) {
            String taskType = "";
            String taskData = "";

            if (task instanceof Todo) {
                taskType = "T";  // Represents a Todo task
                taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((Todo) task).getDescription();
            } else if (task instanceof Deadline) {
                taskType = "D";  // Represents a Deadline task
                taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((Deadline) task).getDescription() + " | " + ((Deadline) task).getDeadlineTime();
            } else if (task instanceof Event) {
                taskType = "E";  // Represents an Event task
                taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((Event) task).getDescription() + " | " + ((Event) task).getFromTime() + " | " + ((Event) task).getToTime();
            }

            // Write the formatted task data to the file
            writer.write(taskData + System.lineSeparator());
        }

        writer.close();  // Close the writer after writing all tasks
    }
}
