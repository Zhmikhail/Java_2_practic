import console.ConsoleManager;
import repository.StudentRepository;
import repository.impl.StudentRepositoryImpl;
import service.StudentService;
import transport.client.SocketClient;
import transport.client.impl.SocketClientImpl;

public class Main {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepositoryImpl();
        SocketClient internalProcessorClient = new SocketClientImpl("localhost", 8081);
        SocketClient externalProcessorClient = new SocketClientImpl("localhost", 8082);

        StudentService studentService = new StudentService(studentRepository, internalProcessorClient, externalProcessorClient);
        ConsoleManager consoleManager = new ConsoleManager(studentService);

        consoleManager.start();
    }
}
