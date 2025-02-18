package file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Tasks.Task;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

public class TaskSaver {
    public static void saveTasks(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        for (Task task : tasks) {
            String taskType = "";
            String taskData = "";

            if (task instanceof Todo) {
                taskType = "T";
                taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((Todo) task).getDescription();
            } else if (task instanceof Deadline) {
                taskType = "D";
                taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((Deadline) task).getDescription() + " | " + ((Deadline) task).getDeadlineTime();
            } else if (task instanceof Event) {
                taskType = "E";
                taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((Event) task).getDescription() + " | " + ((Event) task).getFromTime() + " | " + ((Event) task).getToTime();
            }

            writer.write(taskData + System.lineSeparator());
        }

        writer.close();
    }
}
