package console;

import service.StudentService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleManager {
    private static final Logger logger = Logger.getLogger(ConsoleManager.class.getName());
    private final StudentService studentService;
    private final Console console;

    public ConsoleManager(StudentService studentService) {
        logger.log(Level.SEVERE, "uno 1 ");
        this.studentService = studentService;
        this.console = new Console();
        logger.log(Level.SEVERE, "uno  ");
    }

    public void start() {
        logger.log(Level.SEVERE, "dos 1  ");
        while (true) {
            try {
                logger.log(Level.SEVERE, "dos ");
                String name = console.readLine(EnterType.ENTER_NAME.getEnter());
                int age = console.readInt(EnterType.ENTER_AGE.getEnter());
                String university = console.readLine(EnterType.ENTER_UNIVERSITY.getEnter());
                String specialtyCode = console.readLine(EnterType.ENTER_SPECIALTY.getEnter());
                int diplomaNumber = console.readInt(EnterType.ENTER_DIPLOMA.getEnter());

                studentService.processStudentData(name, age, university, specialtyCode, diplomaNumber);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error processing input: " + e.getMessage(), e);
            }
        }
    }

    private enum EnterType {
        ENTER_NAME("Enter student name:"),
        ENTER_AGE("Enter student age:"),
        ENTER_UNIVERSITY("Enter university:"),
        ENTER_SPECIALTY("Enter specialty code:"),
        ENTER_DIPLOMA("Enter diploma number:");

        private final String enter;

        EnterType(String enter) {
            this.enter = enter;
        }

        public String getEnter() {
            return enter;
        }
    }
}
