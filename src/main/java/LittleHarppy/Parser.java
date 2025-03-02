package LittleHarppy;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import file.Storage;
import LittleHarppy.exception.LittleHarppyException;

import java.util.ArrayList;

/**
 * Responsible for parsing user commands and processing the relevant task actions.
 * This class determines which command was entered by the user and calls the corresponding method
 * to handle the action.
 */
public class Parser {

    /**
     * Processes a given user command and executes the corresponding task.
     *
     * @param input the user input
     * @param tasks the task list to manipulate
     * @param ui the UI to display information to the user
     * @param storage the storage for saving and loading tasks
     * @return false if the user typed 'bye', otherwise true
     */
    public boolean processCommand(String input, TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.equalsIgnoreCase("bye")) {
                return false;  // Ends chatbot loop
            } else if (input.equalsIgnoreCase("list")) {
                tasks.listTasks();
            } else if (input.startsWith("find ")) {
                String keyword = input.substring(5).trim(); // Extract the keyword
                ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
                ui.showFoundTasks(matchingTasks); // Show the found tasks
            } else if (input.startsWith("mark ")) {
                markTask(input, tasks, ui);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input, tasks, ui);
            } else if (input.startsWith("delete ")) {
                deleteTask(input, tasks, ui);
            } else if (input.startsWith("todo ")) {
                addTodo(input, tasks, ui);
            } else if (input.startsWith("deadline ")) {
                addDeadline(input, tasks, ui);
            } else if (input.startsWith("event ")) {
                addEvent(input, tasks, ui);
            } else {
                throw LittleHarppyException.invalidInputFormat();
            }

            // Save tasks after every modification
            storage.save(tasks.getTasks());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    // Helper methods for different task actions

    /**
     * Adds a Todo task to the task list.
     * @param input the user input for creating a Todo task
     * @param tasks the task list to modify
     * @param ui the UI to display output
     * @throws LittleHarppyException if the Todo task description is empty
     */
    private void addTodo(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        String taskDescription = input.substring(5).trim();
        if (taskDescription.isEmpty()) {
            throw LittleHarppyException.emptyTodoDescription();
        }
        Task newTask = new Todo(taskDescription);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.getSize());
    }

    /**
     * Adds a Deadline task to the task list.
     * @param input the user input for creating a Deadline task
     * @param tasks the task list to modify
     * @param ui the UI to display output
     * @throws LittleHarppyException if the Deadline task format is incorrect
     */
    private void addDeadline(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length < 2) {
            throw LittleHarppyException.incorrectDeadlineFormat();
        }
        Task newTask = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.getSize());
    }

    /**
     * Adds an Event task to the task list.
     * @param input the user input for creating an Event task
     * @param tasks the task list to modify
     * @param ui the UI to display output
     * @throws LittleHarppyException if the Event task format is incorrect
     */
    private void addEvent(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        String[] parts = input.substring(6).split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw LittleHarppyException.incorrectEventFormat();
        }
        Task newTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.getSize());
    }

    /**
     * Deletes a task from the task list.
     * @param input the user input to delete a task
     * @param tasks the task list to modify
     * @param ui the UI to display output
     * @throws LittleHarppyException if the task number is invalid
     */
    private void deleteTask(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        int taskIndex = getTaskIndex(input, tasks);
        Task removedTask = tasks.getTask(taskIndex);
        tasks.removeTask(taskIndex);
        ui.showTaskDeleted(removedTask, tasks.getSize());
    }

    /**
     * Marks a task as done.
     * @param input the user input to mark a task
     * @param tasks the task list to modify
     * @param ui the UI to display output
     * @throws LittleHarppyException if the task number is invalid
     */
    private void markTask(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        int taskIndex = getTaskIndex(input, tasks);
        tasks.getTask(taskIndex).markAsDone();
        ui.showTaskMarked(tasks.getTask(taskIndex));
    }

    /**
     * Unmarks a task as not done.
     * @param input the user input to unmark a task
     * @param tasks the task list to modify
     * @param ui the UI to display output
     * @throws LittleHarppyException if the task number is invalid
     */
    private void unmarkTask(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        int taskIndex = getTaskIndex(input, tasks);
        tasks.getTask(taskIndex).markAsNotDone();
        ui.showTaskUnmarked(tasks.getTask(taskIndex));
    }

    /**
     * Retrieves the task index from the user input.
     * @param input the user input containing the task index
     * @param tasks the task list to validate the index
     * @return the valid task index
     * @throws LittleHarppyException if the task index is invalid
     */
    private int getTaskIndex(String input, TaskList tasks) throws LittleHarppyException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw LittleHarppyException.missingTaskNumber();
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw LittleHarppyException.invalidTaskNumber(tasks.getSize());
        }
        return taskIndex;
    }

}
