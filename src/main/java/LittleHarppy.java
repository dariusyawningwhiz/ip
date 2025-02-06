import java.util.ArrayList;
import java.util.Scanner;

public class LittleHarppy {
    static class Task {
        String description;
        boolean isDone;

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

        public String toString() {
            return (isDone ? "[X] " : "[ ] ") + description;
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
        System.out.println("You can add your itinerary by typing <date> <time> <place>.");
        System.out.println("To check your itinerary plan by, type 'list'");
        System.out.println("To remove an itinerary, type 'remove' followed by the numbering on the list");
        System.out.println("To mark visited itinerary, type 'mark' followed by the numbering on the list");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                // Exit message
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                // Display stored tasks
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
            } else {
                // Store and acknowledge input
                tasks.add(new Task(userInput));
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
