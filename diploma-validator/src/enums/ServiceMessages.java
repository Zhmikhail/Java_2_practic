package enums;

public enum ServiceMessages {
    ERROR_COMMUNICATING_WITH_INTERNAL("Error communicating with ministry"),
    ERROR_COMMUNICATING_WITH_EXTERNAL("Error communicating with university"),
    INTERNAL_PROCESSOR_RESPONSE("Ministry processor response: "),
    EXTERNAL_PROCESSOR_RESPONSE("University processor response: "),
    INVALID_UNIVERSITY_OR_SPECIALTY("Invalid university or specialty"),
    SUCCESS_MESSAGE("Validation process completed"),
    STUDENT_DELETED_SUCCESS("Student deleted successfully"),
    STUDENT_UPDATED_SUCCESS("Student updated successfully"),
    STUDENT_NOT_FOUND("Student not found"),
    INVALID_STUDENT_DATA("Invalid student data");

    private final String message;

    ServiceMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
