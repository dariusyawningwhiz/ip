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
 * It reads task data from a file, parses the data, and converts it into Task objects.
 */
public class TasksLoader {
    /**
     * Loads tasks from a file and converts them into Task objects.
     * It parses each line of the file, identifies the task type, and creates the corresponding Task object.
     *
     * @param filePath the file path from which to load the tasks
     * @return an ArrayList of Task objects loaded from the file
     * @throws FileNotFoundException if the file cannot be found
     */
    public static ArrayList<Task> loadTasks(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            return tasks;  // Return an empty list if the file does not exist
        }

        Scanner scanner = new Scanner(file);

        // Read each line of the file and parse the task data
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] taskData = line.split(" \\| ");

            // Validate task data format before proceeding
            if (taskData.length < 3) {
                System.out.println("Invalid task format: " + line);
                continue;
            }

            Task task = null;

            // Depending on the task type, create the appropriate task object
            switch (taskData[0]) {
            case "T":  // Todo task
                if (taskData.length == 3) {
                    task = new Todo(taskData[2]);
                }
                break;
            case "D":  // Deadline task
                if (taskData.length == 4) {
                    task = new Deadline(taskData[2], taskData[3]);
                }
                break;
            case "E":  // Event task
                if (taskData.length == 5) {
                    task = new Event(taskData[2], taskData[3], taskData[4]);
                }
                break;
            default:
                System.out.println("Unknown task type: " + taskData[0]);
                break;
            }

            // If a valid task is created, mark it as done if indicated in the file
            if (task != null) {
                if ("1".equals(taskData[1])) {
                    task.markAsDone();  // Mark the task as done if the second field is "1"
                }
                tasks.add(task);  // Add the task to the task list
            } else {
                System.out.println("Invalid task data: " + line);  // Invalid task data, skip
            }
        }

        scanner.close();  // Close the scanner after reading the file
        return tasks;
    }
}
