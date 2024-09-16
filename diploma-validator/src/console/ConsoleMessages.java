package console;

public enum ConsoleMessages {
    ENTER_CHOICE("Enter 1 to input a new student or 2 to view all students:"),
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
