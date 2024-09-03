package service;

public enum ExternalValidationMessages {
    UNIVERSITY_NOT_FOUND("University not found"),
    SPECIALTY_NOT_FOUND("Specialty code not found"),
    STUDENT_NOT_FOUND("Student with specified diploma number not found"),
    VALIDATION_PASSED("External validation passed");

    private final String message;

    ExternalValidationMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
