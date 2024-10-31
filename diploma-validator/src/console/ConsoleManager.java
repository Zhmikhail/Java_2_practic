package console;

import enums.Commands;
import enums.ConsoleMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.entity.StudentEntity;
import service.StudentService;
import validator.StudentValidator;
import exception.ValidationException;

import java.util.List;
import java.util.Optional;

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
//        try {
//            StudentEntity student = collectStudentData(false, null);
//            studentService.processStudentData(student.getName(), student.getAge(), student.getUniversity(), student.getSpecialtyCode(), student.getDiplomaNumber());
//            console.print(ConsoleMessages.OUTPUT_SUCCESS.getMessage());
//        } catch (Exception e) {
//            console.print(ConsoleMessages.ERROR_PROCESSING.getMessage() + e.getMessage());
//        }
        return;
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

//    private void updateStudent() {
//        try {
//            String idInput = console.readLine(ConsoleMessages.ENTER_ID_OR_SKIP.getMessage());
//            if (!idInput.isEmpty()) {
//                int id = Integer.parseInt(idInput);
//                Optional<StudentEntity> studentOpt = studentService.findById(id);
//                if (studentOpt.isPresent()) {
//                    StudentEntity existingStudent = studentOpt.get();
//                    StudentEntity updatedStudent = collectStudentData(true, existingStudent);
//                    studentService.updateStudentById(id, updatedStudent);
//                    console.print(ConsoleMessages.STUDENT_UPDATED.getMessage());
//                } else {
//                    console.print(ConsoleMessages.STUDENT_NOT_FOUND.getMessage());
//                }
//            } else {
//                console.print(ConsoleMessages.ERROR_INVALID_ID.getMessage());
//            }
//        } catch (Exception e) {
//            console.print(ConsoleMessages.ERROR_PROCESSING.getMessage() + e.getMessage());
//        }
//    }

    private void updateStudent() {
        try {
            String idInput = console.readLine(ConsoleMessages.ENTER_ID_OR_SKIP.getMessage());
            Optional<StudentEntity> studentOpt;

            if (!idInput.isEmpty()) {
                int id = Integer.parseInt(idInput);
                studentOpt = studentService.findById(id);
            } else {
                // Если ID не указан, собираем все данные для поиска
                StudentEntity searchCriteria = collectStudentData(false, null);
                studentOpt = studentService.findByDetails(
                        searchCriteria.getName(),
                        searchCriteria.getAge(),
                        searchCriteria.getUniversity(),
                        searchCriteria.getSpecialtyCode(),
                        searchCriteria.getDiplomaNumber()
                );
            }

            if (studentOpt.isPresent()) {
                StudentEntity existingStudent = studentOpt.get();
                StudentEntity updatedStudent = collectStudentData(true, existingStudent);
                studentService.updateStudentById(existingStudent.getId(), updatedStudent);
                console.print(ConsoleMessages.STUDENT_UPDATED.getMessage());
            } else {
                console.print(ConsoleMessages.STUDENT_NOT_FOUND.getMessage());
            }

        } catch (Exception e) {
            console.print(ConsoleMessages.ERROR_PROCESSING.getMessage() + e.getMessage());
        }
    }


    private void deleteStudent() {
        try {
            String idInput = console.readLine(ConsoleMessages.ENTER_ID_TO_DELETE.getMessage());
            if (!idInput.isEmpty()) {
                int id = Integer.parseInt(idInput);
                studentService.deleteStudentById(id);
                console.print(ConsoleMessages.STUDENT_DELETED.getMessage());
            } else {
                console.print(ConsoleMessages.ERROR_INVALID_ID.getMessage());
            }
        } catch (Exception e) {
            console.print(ConsoleMessages.ERROR_PROCESSING.getMessage() + e.getMessage());
        }
    }

    private StudentEntity collectStudentData(boolean isUpdate, StudentEntity existingStudent) {
        String name = null;
        int age = -1;
        String university = null;
        String specialtyCode = null;
        int diplomaNumber = -1;

        try {
            while (name == null) {
                String inputName = isUpdate && existingStudent != null ?
                        console.readLine(String.format(ConsoleMessages.ENTER_NEW_NAME.getMessage(), existingStudent.getName())) :
                        console.readLine(ConsoleMessages.ENTER_NAME.getMessage());

                if (inputName.isEmpty() && isUpdate && existingStudent != null) {
                    name = existingStudent.getName();
                } else {
                    try {
                        studentValidator.validateName(inputName);
                        name = inputName;
                    } catch (ValidationException e) {
                        console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                    }
                }
            }

            while (age == -1) {
                String inputAge = isUpdate && existingStudent != null ?
                        console.readLine(String.format(ConsoleMessages.ENTER_NEW_AGE.getMessage(), existingStudent.getAge())) :
                        console.readLine(ConsoleMessages.ENTER_AGE.getMessage());

                if (inputAge.isEmpty() && isUpdate && existingStudent != null) {
                    age = existingStudent.getAge();
                } else {
                    try {
                        age = Integer.parseInt(inputAge);
                        studentValidator.validateAge(age);
                    } catch (ValidationException | NumberFormatException e) {
                        console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                        age = -1;
                    }
                }
            }

            while (university == null) {
                String inputUniversity = isUpdate && existingStudent != null ?
                        console.readLine(String.format(ConsoleMessages.ENTER_NEW_UNIVERSITY.getMessage(), existingStudent.getUniversity())) :
                        console.readLine(ConsoleMessages.ENTER_UNIVERSITY.getMessage());

                if (inputUniversity.isEmpty() && isUpdate && existingStudent != null) {
                    university = existingStudent.getUniversity();
                } else {
                    try {
                        studentValidator.validateUniversity(inputUniversity);
                        university = inputUniversity;
                    } catch (ValidationException e) {
                        console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                    }
                }
            }

            while (specialtyCode == null) {
                String inputSpecialtyCode = isUpdate && existingStudent != null ?
                        console.readLine(String.format(ConsoleMessages.ENTER_NEW_SPECIALTY_CODE.getMessage(), existingStudent.getSpecialtyCode())) :
                        console.readLine(ConsoleMessages.ENTER_SPECIALTY_CODE.getMessage());

                if (inputSpecialtyCode.isEmpty() && isUpdate && existingStudent != null) {
                    specialtyCode = existingStudent.getSpecialtyCode();
                } else {
                    try {
                        studentValidator.validateSpecialtyCode(inputSpecialtyCode);
                        specialtyCode = inputSpecialtyCode;
                    } catch (ValidationException e) {
                        console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                    }
                }
            }

            while (diplomaNumber == -1) {
                String inputDiplomaNumber = isUpdate && existingStudent != null ?
                        console.readLine(String.format(ConsoleMessages.ENTER_NEW_DIPLOMA_NUMBER.getMessage(), existingStudent.getDiplomaNumber())) :
                        console.readLine(ConsoleMessages.ENTER_DIPLOMA_NUMBER.getMessage());

                if (inputDiplomaNumber.isEmpty() && isUpdate && existingStudent != null) {
                    diplomaNumber = existingStudent.getDiplomaNumber();
                } else {
                    try {
                        diplomaNumber = Integer.parseInt(inputDiplomaNumber);
                        studentValidator.validateDiplomaNumber(diplomaNumber);
                    } catch (ValidationException | NumberFormatException e) {
                        console.print(ConsoleMessages.ERROR_VALIDATING.getMessage() + e.getMessage());
                        diplomaNumber = -1;
                    }
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
