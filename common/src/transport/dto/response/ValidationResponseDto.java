package transport.dto.response;

import java.io.Serializable;

public class ValidationResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean valid;
    private String message;

    public ValidationResponseDto(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
