package service;

public enum InternalValidationMessages {
    UNIVERSITY_NOT_FOUND("University not found"),
    SPECIALTY_NOT_FOUND("Specialty code not found"),
    VALIDATION_PASSED("Internal validation passed");

    private final String message;

    InternalValidationMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
