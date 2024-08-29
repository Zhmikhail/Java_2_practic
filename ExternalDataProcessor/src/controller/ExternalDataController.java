package controller;

import exception.ValidationException;
import service.ExternalDataService;
import transport.dto.request.StudentRequest;
import transport.dto.response.ValidationResponse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ExternalDataController {
    private static final Logger logger = Logger.getLogger(ExternalDataController.class.getName());
    private final ExternalDataService externalDataService;

    public ExternalDataController(ExternalDataService externalDataService) {
        this.externalDataService = externalDataService;
    }

    public ValidationResponse processStudentData(StudentRequest request) throws ValidationException {
        return externalDataService.validateStudentData(request);
    }

    public void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

                    StudentRequest request = (StudentRequest) input.readObject();
                    ValidationResponse response = processStudentData(request);
                    output.writeObject(response);
                    output.flush();
                } catch (IOException | ClassNotFoundException e) {
                    logger.log(Level.INFO, "EEE-error " + e.getMessage(), e);
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
