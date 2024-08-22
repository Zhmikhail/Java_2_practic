package transport.client.impl;

import transport.dto.response.ValidationResponse;
import transport.client.SocketClient;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketClientImpl implements SocketClient {
    private final String host;
    private final int port;

    public SocketClientImpl(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void sendResponse(ValidationResponse response) {
        try (Socket socket = new Socket(host, port);
             OutputStream outputStream = socket.getOutputStream()) {
            outputStream.write(response.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
