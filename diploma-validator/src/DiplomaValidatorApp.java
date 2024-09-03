import console.Console;
import console.ConsoleManager;
import repository.StudentRepository;
import repository.impl.StudentRepositoryImpl;
import service.StudentService;
import transport.client.SocketClient;
import transport.client.impl.SocketClientImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DiplomaValidatorApp {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("resources/config/application.properties"));

        String internalHost = properties.getProperty("internalProcessor.host");
        int internalPort = Integer.parseInt(properties.getProperty("internalProcessor.port"));
        System.out.println(internalPort);
        String externalHost = properties.getProperty("externalProcessor.host");
        int externalPort = Integer.parseInt(properties.getProperty("externalProcessor.port"));
        String csvFilePath = properties.getProperty("csv.file.path");

        SocketClient internalProcessorClient = new SocketClientImpl(internalHost, internalPort);
        SocketClient externalProcessorClient = new SocketClientImpl(externalHost, externalPort);

        StudentRepository studentRepository = new StudentRepositoryImpl(csvFilePath);
        StudentService studentService = new StudentService(studentRepository, internalProcessorClient, externalProcessorClient);

        Console console = new Console();
        ConsoleManager consoleManager = new ConsoleManager(studentService, console);

        consoleManager.start();
    }
}