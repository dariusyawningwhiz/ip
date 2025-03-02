package file;

import Tasks.Task;
import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return TasksLoader.loadTasks(filePath);
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        TaskSaver.saveTasks(filePath, tasks);
    }
}
