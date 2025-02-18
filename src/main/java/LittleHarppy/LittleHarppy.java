package LittleHarppy;

import LittleHarppy.exception.LittleHarppyException;
import java.io.*;
import java.util.*;
import Tasks.*;
import file.TasksLoader;
import file.TaskSaver;

class LittleHarppy {
    private static final String CHATBOT_NAME = "LittleHarppy";
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "...//main//java//data//duke.txt";
    //C://Users//LENOVO//OneDrive - National University of Singapore//Desktop//Darius//NUS//Y2S2//CS2113 Software Engineering & OOP//ip//src//main//java//data//duke.txt
    public static void main(String[] args) {
        try {
            displayWelcomeMessage();
            loadTasks();

            boolean chatbotIsRunning = true;

            while (chatbotIsRunning) {
                String userInput = scanner.nextLine().trim();

                // Check if user wants to exit
                if (userInput.equalsIgnoreCase("bye")) {
                    displayExitMessage();
                    chatbotIsRunning = false;
                    saveTasks();
                } else {
                    processUserInput(userInput);
                }
            }
        } catch (LittleHarppyException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void displayWelcomeMessage() throws LittleHarppyException {
        printSeparator();
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("  /^_^/");
        System.out.println(" ( o.o ) ");
        System.out.println("  > ~ <");
        System.out.println("\nYou can add tasks typing 'todo ...', 'deadline ...', or 'event ...'.");
        System.out.println("For example: 'todo Buy groceries'\n");
        System.out.println("To check your tasks, type 'list'.");
        System.out.println("To mark a task as done, type 'mark' followed by its number.");
        System.out.println("To unmark a task, type 'unmark' followed by its number.");
        System.out.println("Type 'bye' to exit.");
        printSeparator();
    }

    private static void displayExitMessage() {
        printSeparator();
        System.out.println("Bye! Hope to see you again soon.");
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    private static void processUserInput(String input) {
        try {
            if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                modifyTaskStatus(input, true);
            } else if (input.startsWith("unmark ")) {
                modifyTaskStatus(input, false);
            } else if (input.startsWith("delete ")) {
                deleteTask(input);
            } else if (input.startsWith("todo ")) {
                addTodoTask(input);
            } else if (input.startsWith("deadline ")) {
                addDeadlineTask(input);
            } else if (input.startsWith("event ")) {
                addEventTask(input);
            } else {
                throw LittleHarppyException.invalidInputFormat();
            }
        } catch (LittleHarppyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listTasks() throws LittleHarppyException {
        printSeparator();
        System.out.println("Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            throw LittleHarppyException.taskAdditionError("No tasks to display.");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printSeparator();
    }

    private static void modifyTaskStatus(String input, boolean markAsDone) {
        try {
            String[] inputParts = input.split(" ");
            if (inputParts.length < 2) {
                throw LittleHarppyException.missingTaskNumber();
            }

            int taskIndex = Integer.parseInt(inputParts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw LittleHarppyException.invalidTaskNumber(tasks.size());
            }

            Task task = tasks.get(taskIndex);
            if (markAsDone) {
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  " + task);
            saveTasks();
        } catch (NumberFormatException e) {
            System.out.println(LittleHarppyException.invalidTaskNumberFormat().getMessage());
        } catch (LittleHarppyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteTask(String input) {
        try {
            String[] inputParts = input.split(" ");
            if (inputParts.length < 2) {
                throw LittleHarppyException.missingTaskNumber();
            }

            int taskIndex = Integer.parseInt(inputParts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw LittleHarppyException.taskOutOfRange();
            }

            Task removedTask = tasks.remove(taskIndex);
            printSeparator();
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            printSeparator();
            saveTasks();
        } catch (NumberFormatException e) {
            System.out.println(LittleHarppyException.invalidTaskNumberFormat().getMessage());
        } catch (LittleHarppyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addTodoTask(String input) {
        try {
            String taskDescription = input.substring(5).trim();
            if (taskDescription.isEmpty()) {
                throw LittleHarppyException.emptyTodoDescription();
            }

            Task newTodoTask = new Todo(taskDescription);
            tasks.add(newTodoTask);
            System.out.println("Got it. I've added this task: " + newTodoTask);
            saveTasks();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LittleHarppyException.invalidInputFormat().getMessage());
        } catch (LittleHarppyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addDeadlineTask(String input) {
        try {
            String[] parts = input.substring(9).split(" /by ", 2);
            if (parts.length < 2) {
                throw LittleHarppyException.incorrectDeadlineFormat();
            }

            String description = parts[0].trim();
            String deadlineTime = parts[1].trim();

            if (description.isEmpty() || deadlineTime.isEmpty()) {
                throw LittleHarppyException.incorrectDeadlineFormat();
            }

            Task newTask = new Deadline(description, deadlineTime);
            tasks.add(newTask);
            System.out.println("Got it. I've added this task: " + newTask);
            saveTasks();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LittleHarppyException.invalidInputFormat().getMessage());
        } catch (LittleHarppyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addEventTask(String input) {
        try {
            String[] parts = input.substring(6).split(" /from | /to ", 3);
            if (parts.length < 3) {
                throw LittleHarppyException.incorrectEventFormat();
            }

            String description = parts[0].trim();
            String fromTime = parts[1].trim();
            String toTime = parts[2].trim();

            if (description.isEmpty() || fromTime.isEmpty() || toTime.isEmpty()) {
                throw LittleHarppyException.incorrectEventFormat();
            }

            Task newTask = new Event(description, fromTime, toTime);
            tasks.add(newTask);
            System.out.println("Got it. I've added this task: " + newTask);
            saveTasks();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LittleHarppyException.invalidInputFormat().getMessage());
        } catch (LittleHarppyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void saveTasks() {
        try {
            TaskSaver.saveTasks(FILE_PATH, tasks);  // Use TaskSaver to save tasks
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

    private static void loadTasks() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                ArrayList<Task> loadedTasks = TasksLoader.loadTasks(FILE_PATH);
                tasks.addAll(loadedTasks);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing task file found, starting fresh.");
        }
    }
    private static void doNothing() {};
}
