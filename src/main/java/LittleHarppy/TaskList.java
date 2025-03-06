package LittleHarppy;

import Tasks.Task;
import java.util.ArrayList;

/**
 * A list of tasks in the application.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with an existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets the number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves all the tasks in the task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a task from the task list by its index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Lists all the tasks in the task list.
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
            return;
        }
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Finds tasks that match a given keyword.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
