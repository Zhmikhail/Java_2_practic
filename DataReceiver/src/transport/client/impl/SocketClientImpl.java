package transport.client.impl;

import mapper.StudentMapper;
import transport.client.SocketClient;
import transport.dto.request.StudentRequest;
import transport.dto.response.ValidationResponse;

import java.io.*;
import java.net.Socket;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.logging.Level;

public class SocketClientImpl implements SocketClient {
    private static final Logger logger = Logger.getLogger(SocketClientImpl.class.getName());
    private final String host;
    private final int port;

    public SocketClientImpl(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public ValidationResponse sendData(StudentRequest dataRequest) throws IOException {
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(dataRequest);
            out.flush();  // Добавьте flush
            logger.log(Level.INFO, "Request sent to server");

            ValidationResponse response = (ValidationResponse) in.readObject();
            logger.log(Level.INFO, "Response received from server: " + response);
            return response;

        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Exception during response deserialization: " + e.getMessage(), e);
            throw new IOException("Error in response deserialization", e);
        }
    }
}
