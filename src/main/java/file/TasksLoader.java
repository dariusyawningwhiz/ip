package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;

public class TasksLoader {
    public static ArrayList<Task> loadTasks(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            return tasks;
        }

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] taskData = line.split(" \\| ");

            if (taskData.length < 3) {
                System.out.println("Invalid task format: " + line);
                continue;
            }

            Task task = null;

            switch (taskData[0]) {
            case "T":
                if (taskData.length == 3) {
                    task = new Todo(taskData[2]);
                }
                break;
            case "D":
                if (taskData.length == 4) {
                    task = new Deadline(taskData[2], taskData[3]);
                }
                break;
            case "E":
                if (taskData.length == 5) {
                    task = new Event(taskData[2], taskData[3], taskData[4]);
                }
                break;
            default:
                System.out.println("Unknown task type: " + taskData[0]);
                break;
            }

            if (task != null) {
                if ("1".equals(taskData[1])) {
                    task.markAsDone();
                }
                tasks.add(task);
            } else {
                System.out.println("Invalid task data: " + line);
            }
        }

        scanner.close();
        return tasks;
    }
}