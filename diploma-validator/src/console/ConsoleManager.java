package console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.entity.StudentEntity;
import service.StudentService;
import validator.StudentValidator;
import exception.ValidationException;

import java.util.List;

@Component
public class ConsoleManager {
    private final StudentService studentService;
    private final Console console;
    private final StudentValidator studentValidator;

    @Autowired
    public ConsoleManager(StudentService studentService, Console console) {
        this.studentService = studentService;
        this.console = console;
        this.studentValidator = new StudentValidator();
    }

    public void start() {
        while (true) {
            String scannedCommand = console.readLine("Select command:").toUpperCase();
            try {
                Commands command = Commands.valueOf(scannedCommand);

                switch (command) {
                    case HELP:
                        command.printHelp();
                        break;
                    case ADD:
                        handleStudentInput();
                        break;
                    case READ:
                        viewAllStudents();
                        break;
                    case UPDATE:
                        updateStudent();
                        break;
                    case DELETE:
                        deleteStudent();
                        break;
                    case EXIT:
                        System.exit(0);
                    default:
                        inputError();
                }

            } catch (IllegalArgumentException e) {
                inputError();
            }
        }
    }

    private void handleStudentInput() {
        try {
            StudentEntity student = collectStudentData();
            studentService.processStudentData(student.getName(), student.getAge(), student.getUniversity(), student.getSpecialtyCode(), student.getDiplomaNumber());
            console.print(ConsoleMessages.OUTPUT_SUCCESS.getMessage());
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

    private void updateStudent() {
        try {
            StudentEntity student = collectStudentData();
            studentService.updateStudent(student);
            console.print(ConsoleMessages.STUDENT_UPDATED.getMessage());
        } catch (Exception e) {
            console.print(ConsoleMessages.ERROR_PROCESSING.getMessage() + e.getMessage());
        }
    }

    private void deleteStudent() {
        try {
            StudentEntity student = collectStudentData();
            studentService.deleteStudent(student.getName(), student.getAge(), student.getUniversity(), student.getSpecialtyCode(), student.getDiplomaNumber());
            console.print(ConsoleMessages.STUDENT_DELETED.getMessage());
        } catch (Exception e) {
            console.print(ConsoleMessages.ERROR_PROCESSING.getMessage() + e.getMessage());
        }
    }

    private StudentEntity collectStudentData() {
        String name = null;
        int age = -1;
        String university = null;
        String specialtyCode = null;
        int diplomaNumber = -1;

        try {
            while (name == null) {
                try {
                    name = console.readLine(ConsoleMessages.ENTER_NAME.getMessage());
                    studentValidator.validateName(name);
                } catch (ValidationException e) {
                    console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                    name = null;
                }
            }

            while (age == -1) {
                try {
                    age = console.readInt(ConsoleMessages.ENTER_AGE.getMessage());
                    studentValidator.validateAge(age);
                } catch (ValidationException e) {
                    console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                    age = -1;
                }
            }

            while (university == null) {
                try {
                    university = console.readLine(ConsoleMessages.ENTER_UNIVERSITY.getMessage());
                    studentValidator.validateUniversity(university);
                } catch (ValidationException e) {
                    console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                    university = null;
                }
            }

            while (specialtyCode == null) {
                try {
                    specialtyCode = console.readLine(ConsoleMessages.ENTER_SPECIALTY_CODE.getMessage());
                    studentValidator.validateSpecialtyCode(specialtyCode);
                } catch (ValidationException e) {
                    console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                    specialtyCode = null;
                }
            }

            while (diplomaNumber == -1) {
                try {
                    diplomaNumber = console.readInt(ConsoleMessages.ENTER_DIPLOMA_NUMBER.getMessage());
                    studentValidator.validateDiplomaNumber(diplomaNumber);
                } catch (ValidationException e) {
                    console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                    diplomaNumber = -1;
                }
            }

        } catch (Exception e) {
            console.print(ConsoleMessages.ERROR_PROCESSING.getMessage() + e.getMessage());
        }

        return new StudentEntity(name, age, university, specialtyCode, diplomaNumber);
    }

    private String formatStudent(StudentEntity student) {
        return String.format(ConsoleMessages.OUTPUT_ALL_STUDENTS.getMessage(),
                student.getName(), student.getAge(), student.getUniversity(), student.getSpecialtyCode(), student.getDiplomaNumber());
    }

    private void inputError() {
        console.print(ConsoleMessages.ERROR_INVALID_CHOICE.getMessage());
    }
}
