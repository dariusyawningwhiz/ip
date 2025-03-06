package LittleHarppy;

import LittleHarppy.exception.LittleHarppyException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import file.Storage;

import java.util.ArrayList;

/**
 * Responsible for parsing user commands and processing the relevant task actions.
 */
public class Parser {

    private static final String commandBye = "bye";
    private static final String commandList = "list";
    private static final String commandFind = "find ";
    private static final String commandMark = "mark ";
    private static final String commandUnmark = "unmark ";
    private static final String commandDelete = "delete ";
    private static final String commandTodo = "todo ";
    private static final String commandDeadline = "deadline ";
    private static final String commandEvent = "event ";
    private static final String deadlineSeparator = " /by ";
    private static final String eventSeparatorFrom = " /from ";
    private static final String eventSeparatorTo = " /to ";
    private static final int todoDescriptionIndex = 5;
    private static final int deadlineDescriptionIndex = 9;
    private static final int eventDescriptionIndex = 6;
    private static final int taskIndexOffset = 1;
    private static final int minimumTwoArguments = 2;
    private static final int minimumThreeArguments = 3;
    /**
     * Processes a given user command and executes the corresponding task.
     */
    public boolean processCommand(String input, TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.equalsIgnoreCase(commandBye)) {
                return false;
            } else if (input.equalsIgnoreCase(commandList)) {
                tasks.listTasks();
            } else if (input.startsWith(commandFind)) {
                String keyword = input.substring(commandFind.length()).trim();
                ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
                ui.showFoundTasks(matchingTasks);
            } else if (input.startsWith(commandMark)) {
                markTask(input, tasks, ui);
            } else if (input.startsWith(commandUnmark)) {
                unmarkTask(input, tasks, ui);
            } else if (input.startsWith(commandDelete)) {
                deleteTask(input, tasks, ui);
            } else if (input.startsWith(commandTodo)) {
                addTodo(input, tasks, ui);
            } else if (input.startsWith(commandDeadline)) {
                addDeadline(input, tasks, ui);
            } else if (input.startsWith(commandEvent)) {
                addEvent(input, tasks, ui);
            } else {
                throw LittleHarppyException.invalidInputFormat();
            }
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    /**
     * Helper methods for different task actions
     */
    private void addTodo(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        String taskDescription = input.substring(todoDescriptionIndex).trim();
        if (taskDescription.isEmpty()) {
            throw LittleHarppyException.emptyTodoDescription();
        }
        Task newTask = new Todo(taskDescription);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.getSize());
    }

    /**
     * Adds a Deadline task to the task list.
     */
    private void addDeadline(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        String[] deadlineInfo = input
                .substring(deadlineDescriptionIndex)
                .split(deadlineSeparator, minimumTwoArguments);
        if (deadlineInfo.length < minimumTwoArguments) {
            throw LittleHarppyException.incorrectDeadlineFormat();
        }
        Task newTask = new Deadline(deadlineInfo[0].trim(), deadlineInfo[1].trim());
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.getSize());
    }

    /**
     * Adds an Event task to the task list.
     */
    private void addEvent(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        String[] eventsInfo = input
                .substring(eventDescriptionIndex)
                .split(eventSeparatorFrom + "|" + eventSeparatorTo, minimumThreeArguments);
        if (eventsInfo.length < minimumThreeArguments) {
            throw LittleHarppyException.incorrectEventFormat();
        }
        Task newTask = new Event(eventsInfo[0].trim(), eventsInfo[1].trim(), eventsInfo[2].trim());
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.getSize());
    }

    /**
     * Deletes a task from the task list.
     */
    private void deleteTask(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        int taskIndex = getTaskIndex(input, tasks);
        Task removedTask = tasks.getTask(taskIndex);
        tasks.removeTask(taskIndex);
        ui.showTaskDeleted(removedTask, tasks.getSize());
    }

    /**
     * Marks a task as done.
     */
    private void markTask(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        int taskIndex = getTaskIndex(input, tasks);
        tasks.getTask(taskIndex).markAsDone();
        ui.showTaskMarked(tasks.getTask(taskIndex));
    }

    /**
     * Unmarks a task as not done.
     */
    private void unmarkTask(String input, TaskList tasks, Ui ui) throws LittleHarppyException {
        int taskIndex = getTaskIndex(input, tasks);
        tasks.getTask(taskIndex).markAsNotDone();
        ui.showTaskUnmarked(tasks.getTask(taskIndex));
    }

    /**
     * Retrieves the task index from the user input.
     */
    private int getTaskIndex(String input, TaskList tasks) throws LittleHarppyException {
        String[] taskInfo = input.split(" ");
        if (taskInfo.length < minimumTwoArguments) {
            throw LittleHarppyException.missingTaskNumber();
        }
        int taskIndex = Integer.parseInt(taskInfo[1]) - taskIndexOffset;
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw LittleHarppyException.invalidTaskNumber(tasks.getSize());
        }
        return taskIndex;
    }

}
