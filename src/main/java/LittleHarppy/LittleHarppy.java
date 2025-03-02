package LittleHarppy;

import file.Storage;
import java.io.*;

public class LittleHarppy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

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

    public void run() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.getUserInput();
            isRunning = parser.processCommand(userInput, tasks, ui, storage);
        }
        ui.showExitMessage();
    }

    public static void main(String[] args) {
        new LittleHarppy("//C://Users//LENOVO//OneDrive - National University of Singapore//Desktop//Darius//NUS//Y2S2//CS2113 Software Engineering & OOP//ip//src//main//java//data//LittleHarppy.txt").run();
        //"data/LittleHarppy.txt"
        //to test on command-prompt: java -jar "C:\Users\LENOVO\OneDrive - National University of Singapore\Desktop\Darius\NUS\Y2S2\CS2113 Software Engineering & OOP\ip\out\artifacts\ip_jar2\ip.jar"
    }
}

