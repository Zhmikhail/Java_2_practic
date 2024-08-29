package transport.dto.response;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidationResponse implements Serializable {
    private static final long serialVersionUID = 1L;  // Добавлен serialVersionUID
    private boolean valid;
    private String message;
    private static final Logger logger = Logger.getLogger(ValidationResponse.class.getName());


    public ValidationResponse(boolean valid, String message) {
        logger.log(Level.INFO, "after out.writeObject(dataRequest)");
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}
