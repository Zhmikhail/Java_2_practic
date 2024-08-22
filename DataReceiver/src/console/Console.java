package console;

import java.util.Scanner;

public class Console {
    private final Scanner scanner;

    public Console() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public int readInt(String message) {
        System.out.println(message);
        return Integer.parseInt(scanner.nextLine());
    }

    public void print(String message) {
        System.out.println(message);
    }
}
