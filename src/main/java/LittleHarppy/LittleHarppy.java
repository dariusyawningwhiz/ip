package LittleHarppy;

import file.Storage;
import java.io.*;

/**
 * The main class for the LittleHarppy application.
 * This class is responsible for setting up and running the application,
 * managing the interaction with the user and loading/saving tasks.
 */
public class LittleHarppy {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs a new LittleHarppy application.
     *
     * @param filePath the file path to load/save task data
     */
    public LittleHarppy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the LittleHarppy application.
     * This method handles user input and displays relevant output until 'bye' is typed.
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.getUserInput();
            isRunning = parser.processCommand(userInput, tasks, ui, storage);
        }
        ui.showExitMessage();
    }

    /**
     * Main method to run the LittleHarppy application.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new LittleHarppy("//C://Users//LENOVO//OneDrive - National University of Singapore//Desktop//Darius//NUS//Y2S2//CS2113 Software Engineering & OOP//ip//src//main//java//data//LittleHarppy.txt").run();
    }
}
