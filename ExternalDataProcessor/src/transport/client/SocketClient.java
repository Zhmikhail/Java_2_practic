package transport.client;

import transport.dto.response.ValidationResponse;

public interface SocketClient {
    void sendResponse(ValidationResponse response);
}
