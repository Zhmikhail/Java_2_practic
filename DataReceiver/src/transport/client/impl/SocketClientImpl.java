package transport.client.impl;

import mapper.StudentMapper;
import transport.client.SocketClient;
import transport.dto.request.StudentRequest;
import transport.dto.response.ValidationResponse;

import java.io.*;
import java.net.Socket;

public class SocketClientImpl implements SocketClient {
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
            ValidationResponse response = (ValidationResponse) in.readObject();
            out.flush();
            return response;

        } catch (ClassNotFoundException e) {
            throw new IOException("Error in response deserialization", e);
        }
    }
}
