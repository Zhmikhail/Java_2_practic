package transport.client;

import transport.dto.request.StudentRequest;
import transport.dto.response.ValidationResponse;
import java.io.IOException;

public interface SocketClient {
    ValidationResponse sendData(StudentRequest dataRequest) throws IOException;
}
