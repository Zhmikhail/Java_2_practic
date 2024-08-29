package service.handler;

import exception.CheckException;
import exception.ConnectException;
import transport.client.SocketClient;
import transport.dto.request.StudentRequest;
import transport.dto.response.ValidationResponse;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DataProcessorHandler {
    private static final Logger logger = Logger.getLogger(DataProcessorHandler.class.getName());

    private final SocketClient internalProcessorClient;
    private final SocketClient externalProcessorClient;

    public DataProcessorHandler(SocketClient internalProcessorClient, SocketClient externalProcessorClient) {
        this.internalProcessorClient = internalProcessorClient;
        this.externalProcessorClient = externalProcessorClient;
    }

    public void processData(StudentRequest request) throws CheckException, ConnectException {
        try {
            ValidationResponse internalResponse = internalProcessorClient.sendData(request);
            logger.log(Level.INFO, "Internal processor response: " + internalResponse.isValid());

            if (!internalResponse.isValid()) {
                throw new CheckException("Invalid university or specialty");
            }

            ValidationResponse externalResponse = externalProcessorClient.sendData(request);
            logger.log(Level.INFO, "External processor response: " + externalResponse.isValid());

            if (!externalResponse.isValid()) {
                throw new CheckException("Invalid student data");
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error communicating with data processor", e);
            throw new ConnectException("Error communicating with data processor");
        }
    }
}
