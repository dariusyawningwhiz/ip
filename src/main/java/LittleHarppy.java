import java.util.ArrayList;
import java.util.Scanner;

public class LittleHarppy {
    static abstract class Task {
        protected String description;
        protected boolean isDone;

        Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        void markAsDone() {
            this.isDone = true;
        }

        void markAsNotDone() {
            this.isDone = false;
        }

        public String getStatusIcon() {
            return isDone ? "[X]" : "[ ]";
        }

        @Override
        public abstract String toString();
    }

    static class Todo extends Task {
        Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + getStatusIcon() + " " + description;
        }
    }

    static class Deadline extends Task {
        private String by;

        Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + getStatusIcon() + " " + description + " (by: " + by + ")";
        }
    }

    static class Event extends Task {
        private String from;
        private String to;

        Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
        }
    }

    public static void main(String[] args) {
        String chatbotName = "LittleHarppy";
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        // Greeting message
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("I am your travel buddy.");
        System.out.println("You can add tasks using 'todo', 'deadline', or 'event'.");
        System.out.println("To check your tasks, type 'list'.");
        System.out.println("To remove a task, type 'remove' followed by its number.");
        System.out.println("To mark a task as done, type 'mark' followed by its number.");
        System.out.println("To unmark a task, type 'unmark' followed by its number.");
        System.out.println("Type 'bye' to exit.");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("mark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                    tasks.get(taskIndex).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(taskIndex));
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("Invalid task number.");
                }
            } else if (userInput.startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    tasks.get(taskIndex).markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(taskIndex));
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("Invalid task number.");
                }
            } else if (userInput.startsWith("remove ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    Task removedTask = tasks.remove(taskIndex);
                    System.out.println("____________________________________________________________");
                    System.out.println("Removed task: " + removedTask);
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("Invalid task number.");
                }
            } else if (userInput.startsWith("todo ")) {
                String description = userInput.substring(5).trim();
                Task newTask = new Todo(description);
                tasks.add(newTask);
                printTaskAdded(newTask, tasks.size());
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ", 2);
                if (parts.length < 2) {
                    System.out.println("Invalid format. Use: deadline <description> /by <time>");
                    continue;
                }
                Task newTask = new Deadline(parts[0].trim(), parts[1].trim());
                tasks.add(newTask);
                printTaskAdded(newTask, tasks.size());
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ", 3);
                if (parts.length < 3) {
                    System.out.println("Invalid format. Use: event <description> /from <time> /to <time>");
                    continue;
                }
                Task newTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                tasks.add(newTask);
                printTaskAdded(newTask, tasks.size());
            } else {
                System.out.println("Invalid command! Use 'todo', 'deadline', or 'event'.");
            }
        }

        scanner.close();
    }

    private static void printTaskAdded(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
