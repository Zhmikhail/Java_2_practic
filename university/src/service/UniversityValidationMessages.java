package service;

public enum UniversityValidationMessages {
    UNIVERSITY_NOT_FOUND("University not found"),
    SPECIALTY_NOT_FOUND("Specialty code not found"),
    STUDENT_NOT_FOUND("Student with specified diploma number not found"),
    VALIDATION_PASSED("External validation passed");

    private final String message;

    UniversityValidationMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
