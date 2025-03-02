package LittleHarppy;

import Tasks.Task;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

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

    public void showExitMessage() {
        printSeparator();
        System.out.println("Bye! Hope to see you again soon.");
        printSeparator();
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting fresh.");
    }

    public String getUserInput() {
        return scanner.nextLine().trim();
    }

    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    public void showTaskAdded(Task task, int taskCount) {  // ✅ Fix method signature
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int taskCount) {  // ✅ Fix method signature
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks left.");
    }

    public void showTaskMarked(Task task) {  // ✅ Fix method signature
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }
}

