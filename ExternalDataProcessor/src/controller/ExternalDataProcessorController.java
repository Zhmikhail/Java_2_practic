package controller;

import exception.ValidationException;
import service.StudentService;
import transport.dto.request.StudentRequest;
import transport.dto.response.ValidationResponse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ExternalDataProcessorController {
    private final StudentService studentService;

    public ExternalDataProcessorController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Метод для обработки данных
    public ValidationResponse processStudentData(StudentRequest request) throws ValidationException {
        return StudentService.validateStudent(request);
    }

    public void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("InternalDataProcessor server started on port " + port);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

                    StudentRequest request = (StudentRequest) input.readObject();
                    ValidationResponse response = processStudentData(request);

                    output.writeObject(response);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
