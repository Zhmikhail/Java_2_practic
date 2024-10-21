package transport.client.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import transport.client.SocketClient;
import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;

import java.io.*;
import java.net.Socket;

@Component
public class SocketClientImpl implements SocketClient {

    @Value("{$internalProcessor.host}")
    private final String host;
    @Value("{$internalProcessor.port}")
    private final int port;

    public SocketClientImpl(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public ValidationResponseDto sendData(StudentRequestDto dataRequest) throws IOException {
        try (Socket socket = new Socket(host, port);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            out.writeObject(dataRequest);
            ValidationResponseDto response = (ValidationResponseDto) in.readObject();
            out.flush();
            return response;

        } catch (ClassNotFoundException e) {
            throw new IOException("Error in response deserialization", e);
        }
    }

    public void sendLog(String header, String message) throws IOException {
        try (Socket socket = new Socket(host, port);
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream()))) {

            writer.write(header);
            writer.newLine();
            writer.write(message);
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            System.out.println("Monitoring service is down");
        }
    }
}
