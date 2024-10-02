package service;

public enum MinistryValidationMessages {
    UNIVERSITY_NOT_FOUND("University not found"),
    SPECIALTY_NOT_FOUND("Specialty code not found"),
    VALIDATION_PASSED("Internal validation passed");

    private final String message;

    MinistryValidationMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
