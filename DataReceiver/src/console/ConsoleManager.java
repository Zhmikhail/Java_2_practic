package console;

import controller.StudentController;
import java.util.Scanner;

public class ConsoleManager {
    private final StudentController studentController;

    public ConsoleManager(StudentController studentController) {
        this.studentController = studentController;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student name:");
        String name = scanner.nextLine();

        System.out.println("Enter student age:");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter university:");
        String university = scanner.nextLine();

        System.out.println("Enter specialty code:");
        String specialtyCode = scanner.nextLine();

        System.out.println("Enter diploma number:");
        int diplomaNumber = scanner.nextInt();

        studentController.handleStudentInput(name, age, university, specialtyCode, diplomaNumber);
    }
}
