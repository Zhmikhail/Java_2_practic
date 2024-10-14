package enums;

public enum ConsoleMessages {
    ENTER_ID_OR_SKIP("Enter student ID or press Enter to skip:"),
    ENTER_ID_TO_DELETE("Enter student ID to delete:"),
    ERROR_INVALID_ID("Invalid or missing student ID."),
    STUDENT_NOT_FOUND("Student not found."),
    STUDENT_UPDATED("Student data updated successfully!"),
    STUDENT_DELETED("Student deleted successfully!"),
    ENTER_NEW_NAME("Enter new name or press Enter to keep current (%s):"),
    ENTER_NEW_AGE("Enter new age or press Enter to keep current (%d):"),
    ENTER_NEW_UNIVERSITY("Enter new university or press Enter to keep current (%s):"),
    ENTER_NEW_SPECIALTY_CODE("Enter new specialty code or press Enter to keep current (%s):"),
    ENTER_NEW_DIPLOMA_NUMBER("Enter new diploma number or press Enter to keep current (%d):"),
    ENTER_NAME("Enter student name:"),
    ENTER_AGE("Enter student age:"),
    ENTER_UNIVERSITY("Enter university:"),
    ENTER_SPECIALTY_CODE("Enter specialty code:"),
    ENTER_DIPLOMA_NUMBER("Enter diploma number:"),
    ERROR_INVALID_CHOICE("Invalid choice, please try 'help'"),
    OUTPUT_ALL_STUDENTS("Name: %s, Age: %d, University: %s, Specialty Code: %s, Diploma Number: %d"),
    ERROR_NUMBER("Invalid input. Please enter a number."),
    ERROR_PROCESSING("Error processing student data: "),
    ERROR_VALIDATING("Error validating student data: "),
    OUTPUT_NO_STUDENTS("No students found."),
    OUTPUT_SUCCESS("Student data processed successfully!"),
    STUDENTS_LIST("Here are all the students:");

    private final String message;

    ConsoleMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
