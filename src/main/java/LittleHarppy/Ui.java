package LittleHarppy;

import Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The user interface for the LittleHarppy application.
 * This class handles the display of messages to the user and the gathering of user input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui object with a scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        printSeparator();
        System.out.println("Hello! I'm LittleHarppy");
        System.out.println("  /^_^/");
        System.out.println(" ( o.o ) ");
        System.out.println("  > ~ <");
        System.out.println("\nYou can add tasks by typing 'todo ...', 'deadline ...', or 'event ...'.");
        System.out.println("To check your tasks, type 'list'.");
        System.out.println("To mark a task as done, type 'mark' followed by its number.");
        System.out.println("To unmark a task, type 'unmark' followed by its number.");
        System.out.println("Type 'bye' to exit.");
        printSeparator();
    }

    /**
     * Displays the exit message when the user exits the application.
     */
    public void showExitMessage() {
        printSeparator();
        System.out.println("Bye! Hope to see you again soon.");
        printSeparator();
    }

    /**
     * Displays an error message when tasks fail to load.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting fresh.");
    }

    /**
     * Retrieves the user input from the command line.
     *
     * @return the user input
     */
    public String getUserInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints a separator line to format the output.
     */
    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    public void showTaskAdded(Task task, int taskCount) {  
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int taskCount) {  
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks left.");
    }

    public void showTaskMarked(Task task) {  
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message indicating that a task was unmarked.
     *
     * @param task the task that was unmarked
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays a list of tasks that match the search keyword.
     *
     * @param tasks the list of tasks to display
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the matching tasks in your list:");
            int index = 1;
            for (Task task : tasks) {
                System.out.println(index++ + ". " + task);  // Assuming task.toString() gives the task format
            }
            System.out.println("____________________________________________________________");
        }
    }
}
