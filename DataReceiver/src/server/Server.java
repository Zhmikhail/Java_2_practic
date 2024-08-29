package server;

import console.ConsoleManager;
import controller.StudentController;

public class Server {
    public void start() {
        StudentController studentController = new StudentController();

        ConsoleManager consoleManager = new ConsoleManager(studentController);
        consoleManager.start();
    }
}
