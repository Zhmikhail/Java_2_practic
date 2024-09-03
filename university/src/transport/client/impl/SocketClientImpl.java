package transport.client.impl;

import transport.client.SocketClient;
import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.*;
import java.net.Socket;

public class SocketClientImpl implements SocketClient {
    private final String host;
    private final int port;
    private static final Logger logger = Logger.getLogger(SocketClientImpl.class.getName());

    public SocketClientImpl(String host, int port) {
        this.host = host;
        this.port = port;
        logger.log(Level.INFO, "Starting myMethod1");
    }

    @Override
    public ValidationResponseDto sendData(StudentRequestDto dataRequest) throws IOException {
        try (Socket socket = new Socket(host, port);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            logger.log(Level.INFO, "before out.writeObject(dataRequest)");
            out.writeObject(dataRequest);
            logger.log(Level.INFO, "after out.writeObject(dataRequest)");
            return (ValidationResponseDto) in.readObject();
        } catch (ClassNotFoundException e) {
            logger.log(Level.INFO, "Starting myMethod3");
            throw new IOException("Error in response deserialization", e);
        }
    }
}
