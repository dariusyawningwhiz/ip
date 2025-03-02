package LittleHarppy;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import file.Storage;
import LittleHarppy.exception.LittleHarppyException;

public class Parser {
    public boolean processCommand(String input, TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.equalsIgnoreCase("bye")) {
                return false;  // Ends chatbot loop
            } else if (input.equalsIgnoreCase("list")) {
                tasks.listTasks();
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

    private void addTodo(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        String taskDescription = input.substring(5).trim();
        if (taskDescription.isEmpty()) {
            throw LittleHarppyException.emptyTodoDescription();
        }
        Task newTask = new Todo(taskDescription);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.getSize());
    }

    private void addDeadline(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length < 2) {
            throw LittleHarppyException.incorrectDeadlineFormat();
        }
        Task newTask = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.getSize());
    }

    private void addEvent(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        String[] parts = input.substring(6).split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw LittleHarppyException.incorrectEventFormat();
        }
        Task newTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.getSize());
    }

    private void deleteTask(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        int taskIndex = getTaskIndex(input, tasks);
        Task removedTask = tasks.getTask(taskIndex);
        tasks.removeTask(taskIndex);
        ui.showTaskDeleted(removedTask, tasks.getSize());
    }

    private void markTask(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        int taskIndex = getTaskIndex(input, tasks);
        tasks.getTask(taskIndex).markAsDone();
        ui.showTaskMarked(tasks.getTask(taskIndex));
    }

    private void unmarkTask(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        int taskIndex = getTaskIndex(input, tasks);
        tasks.getTask(taskIndex).markAsNotDone();
        ui.showTaskUnmarked(tasks.getTask(taskIndex));
    }

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

