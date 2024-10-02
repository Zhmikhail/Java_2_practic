package transport.dto.response;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidationResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean valid;
    private String message;
    private static final Logger logger = Logger.getLogger(ValidationResponseDto.class.getName());

    public ValidationResponseDto(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
        logger.log(Level.INFO, "ValidationResponseDto created with validity: " + valid);
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}
