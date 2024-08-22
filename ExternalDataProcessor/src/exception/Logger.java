package exception;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Logger {
    private static final String LOG_FILE = "log.txt";

    public static void log(String message) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.printf("%s - %s%n", LocalDateTime.now(), message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
