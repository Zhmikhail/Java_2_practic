package transport.client;

import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;

import java.io.IOException;

public interface SocketClient {
    ValidationResponseDto sendData(StudentRequestDto dataRequest) throws IOException;
    void sendLog(String header, String message) throws IOException;
}
