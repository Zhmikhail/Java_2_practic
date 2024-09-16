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
            String scannedCommand = console.readLine("Select command").toUpperCase();
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
                    case EXIT:
                        System.exit(0);
                    default:
                        inputError();
                }

            } catch (NumberFormatException e) {
                console.print(ConsoleMessages.ERROR_NUMBER.getMessage());
            }
            catch (IllegalArgumentException e) {
                inputError();}
        }
    }

    private void handleStudentInput() {
        String name = null;
        int age = -1;
        String university = null;
        String specialtyCode = null;
        int diplomaNumber = -1;

        try {
            // Ввод имени
            while (name == null) {
                try {
                    name = console.readLine(ConsoleMessages.ENTER_NAME.getMessage());
                    studentValidator.validateName(name);
                    studentService.processMonitoringData("created", "Obrabotka studenta");
                } catch (ValidationException e) {
                    console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                    name = null; // Обнуляем имя, чтобы запросить его повторно
                }
            }

            // Ввод возраста
            while (age == -1) {
                try {
                    age = console.readInt(ConsoleMessages.ENTER_AGE.getMessage());
                    studentValidator.validateAge(age);
                } catch (ValidationException e) {
                    console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                    age = -1; // Обнуляем возраст, чтобы запросить его повторно
                }
            }

            // Ввод университета
            university = console.readLine(ConsoleMessages.ENTER_UNIVERSITY.getMessage());

            // Ввод кода специальности
            while (specialtyCode == null) {
                try {
                    specialtyCode = console.readLine(ConsoleMessages.ENTER_SPECIALTY_CODE.getMessage());
                    studentValidator.validateSpecialtyCode(specialtyCode);
                } catch (ValidationException e) {
                    console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                    specialtyCode = null; // Обнуляем код специальности для повторного запроса
                }
            }

            // Ввод номера диплома
            while (diplomaNumber == -1) {
                try {
                    diplomaNumber = console.readInt(ConsoleMessages.ENTER_DIPLOMA_NUMBER.getMessage());
                    studentValidator.validateDiplomaNumber(diplomaNumber);
                } catch (ValidationException e) {
                    console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                    diplomaNumber = -1; // Обнуляем номер диплома для повторного ввода
                }
            }

            // Обработка данных студента
            studentService.processStudentData(name, age, university, specialtyCode, diplomaNumber);
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

    private String formatStudent(StudentEntity student) {
        return String.format(ConsoleMessages.OUTPUT_ALL_STUDENTS.getMessage(),
                student.getName(), student.getAge(), student.getUniversity(), student.getSpecialtyCode(), student.getDiplomaNumber());
    }

    private void inputError() {
        console.print(ConsoleMessages.ERROR_INVALID_CHOICE.getMessage());
    }
}
