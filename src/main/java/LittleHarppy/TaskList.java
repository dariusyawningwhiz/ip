package LittleHarppy;

import Tasks.Task;
import java.util.ArrayList;

/**
 * A list of tasks in the application.
 * This class provides methods to add, remove, list, and search tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with an existing list of tasks.
     *
     * @param tasks the existing list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list by its index.
     *
     * @param index the index of the task to be removed
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return the size of the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves all the tasks in the task list.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index the index of the task
     * @return the task at the given index
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
     *
     * @param keyword the keyword to search for in task descriptions
     * @return a list of tasks that contain the keyword
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
