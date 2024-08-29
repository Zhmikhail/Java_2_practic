package controller;

import exception.ValidationException;
import exception.ConnectException;
import exception.CheckException;
import repository.StudentRepository;
import repository.impl.StudentRepositoryImpl;
import service.StudentService;
import service.handler.DataProcessorHandler;
import transport.client.SocketClient;
import transport.client.impl.SocketClientImpl;

public class StudentController {
    private final StudentService studentService;

    public StudentController() {
        StudentRepository studentRepository = new StudentRepositoryImpl();
        SocketClient internalProcessorClient = new SocketClientImpl("localhost", 8081);
        SocketClient externalProcessorClient = new SocketClientImpl("localhost", 8082);
        DataProcessorHandler dataProcessorHandler = new DataProcessorHandler(internalProcessorClient, externalProcessorClient);

        this.studentService = new StudentService(studentRepository, dataProcessorHandler);
    }

    public void handleStudentInput(String name, int age, String university, String specialtyCode, int diplomaNumber) {
        try {
            studentService.processStudentData(name, age, university, specialtyCode, diplomaNumber);
        } catch (ValidationException | ConnectException | CheckException e) {
            System.out.println("Error processing student data: " + e.getMessage());
        }
    }
}
