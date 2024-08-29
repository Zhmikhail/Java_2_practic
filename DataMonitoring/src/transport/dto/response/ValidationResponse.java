package transport.dto.response;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ValidationResponse implements Serializable {
    private static final long serialVersionUID = 1L;  // Добавлен serialVersionUID
    private static final Logger logger = Logger.getLogger(ValidationResponse.class.getName());
    private boolean valid;
    private String message;

    public ValidationResponse(boolean valid, String message) {
        logger.log(Level.INFO, "Starting myMethod1");
        this.valid = valid;
        this.message = message;
        logger.log(Level.INFO, message);
        logger.log(Level.INFO, String. valueOf(valid));
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
