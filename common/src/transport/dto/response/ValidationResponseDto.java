package transport.dto.response;


import java.io.Serializable;

public class ValidationResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean valid;
    private String message;

    // Конструктор по умолчанию
    public ValidationResponseDto() {}

    // Конструктор с параметрами, если вы хотите использовать его для создания объекта
    public ValidationResponseDto( boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    // Геттеры и сеттеры
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
