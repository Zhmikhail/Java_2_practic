package console;

import repository.entity.StudentEntity;
import service.StudentService;
import validator.StudentValidator;
import exception.ValidationException;
import java.util.List;

public class ConsoleManager {
    private final StudentService studentService;
    private final Console console;
    private final StudentValidator studentValidator;

    public ConsoleManager(StudentService studentService, Console console) {
        this.studentService = studentService;
        this.console = console;
        this.studentValidator = new StudentValidator();
    }

    public void start() throws NumberFormatException {
        while (true) {
            try {
                int choice = console.readInt(ConsoleMessages.ENTER_CHOICE.getMessage());

                switch (choice) {
                    case 1:
                        handleStudentInput();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    default:
                        inputError();
                }
            } catch (NumberFormatException e) {
                console.print(ConsoleMessages.ERROR_NUMBER.getMessage());
            }
        }
    }

    private void handleStudentInput() {
        try {
            String name = console.readLine(ConsoleMessages.ENTER_NAME.getMessage());
            studentValidator.validateName(name);

            int age = console.readInt(ConsoleMessages.ENTER_AGE.getMessage());
            studentValidator.validateAge(age);

            String university = console.readLine(ConsoleMessages.ENTER_UNIVERSITY.getMessage());

            String specialtyCode = console.readLine(ConsoleMessages.ENTER_SPECIALTY_CODE.getMessage());
            studentValidator.validateSpecialtyCode(specialtyCode);

            int diplomaNumber = console.readInt(ConsoleMessages.ENTER_DIPLOMA_NUMBER.getMessage());
            studentValidator.validateDiplomaNumber(diplomaNumber);

            studentService.processStudentData(name, age, university, specialtyCode, diplomaNumber);
            console.print(ConsoleMessages.OUTPUT_SUCCESS.getMessage());

        } catch (ValidationException e) {
            console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
        } catch (Exception e) {
            console.print(ConsoleMessages.ERROR_PROCESSING.getMessage() + e.getMessage());
        }
    }

    private void viewAllStudents() {
        console.print(ConsoleMessages.STUDENTS_LIST.getMessage());
        List<StudentEntity> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            console.print(ConsoleMessages.OUTPUT_NO_STUDENTS.getMessage());
        } else {
            for (StudentEntity student : students) {
                console.print(formatStudent(student));
            }
        }
    }

    private String formatStudent(StudentEntity student) {
        return String.format(ConsoleMessages.OUTPUT_ALL_STUDENTS.getMessage(),
                student.getName(), student.getAge(), student.getUniversity(), student.getSpecialtyCode(), student.getDiplomaNumber());
    }

    private void inputError() {
        console.print(ConsoleMessages.ERROR_INVALID_CHOICE.getMessage());
    }
}
