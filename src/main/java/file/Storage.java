package file;

import Tasks.Task;
import java.io.*;
import java.util.ArrayList;

/**
 * This class is responsible for managing the loading and saving of task data to and from a file.
 * It interacts with other classes such as TaskLoader and TaskSaver to load and save task information.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task data from the specified file.
     * If the file does not exist, an empty list of tasks is returned.
     * @throws IOException if there is an error while reading the file
     */
    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();  // Return an empty list if the file does not exist
        }
        return TasksLoader.loadTasks(filePath);  // Use TasksLoader to read the tasks
    }

    /**
     * Saves the list of tasks to the specified file.
     * @throws IOException if there is an error while writing to the file
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        TaskSaver.saveTasks(filePath, tasks);  // Delegate saving tasks to TaskSaver
    }
}
