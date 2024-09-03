package service;

public enum ServiceMessages {
    ERROR_COMMUNICATING_WITH_INTERNAL("Error communicating with internal data processor"),
    ERROR_COMMUNICATING_WITH_EXTERNAL("Error communicating with external data processor"),
    INTERNAL_PROCESSOR_RESPONSE("Internal processor response: "),
    EXTERNAL_PROCESSOR_RESPONSE("External processor response: "),
    INVALID_UNIVERSITY_OR_SPECIALTY("Invalid university or specialty"),
    SUCCESS_MESSAGE("Validation process completed"),
    INVALID_STUDENT_DATA("Invalid student data");

    private final String message;

    ServiceMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
