package service;

import repository.entity.LogEntity;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class MonitoringService {
    private Path logDir;
    private String fileNamePrefix;
    private static final long maxFileSize = 1024 * 1024; // Максимальный размер файла (1 МБ)
    private long currentFileSize;
    private File currentLogFile;
    private DateTimeFormatter dateFormatter;

    public MonitoringService(Properties properties) throws IOException {
        this.logDir = Paths.get(properties.getProperty("log.directory"));
        this.fileNamePrefix = properties.getProperty("log.file.prefix");
        this.dateFormatter = DateTimeFormatter.ofPattern(properties.getProperty("log.date.pattern"));

        if (!Files.exists(logDir)) {
            Files.createDirectories(logDir);
        }

        rotateLogFile();
    }

    // Метод для ротации файла
    private void rotateLogFile() throws IOException {
        String timestamp = LocalDateTime.now().format(dateFormatter);
        String newLogFileName = fileNamePrefix + "_" + timestamp + ".bin";
        currentLogFile = logDir.resolve(newLogFileName).toFile();
        currentFileSize = 0;

        if (!currentLogFile.exists()) {
            currentLogFile.createNewFile();
        }
    }

    // Метод для записи лога
    public void log(LogEntity entry) throws IOException {
        String logData = entry.toString();
        if (currentFileSize + logData.length() > maxFileSize) {
            rotateLogFile();
        }

        try (FileOutputStream fos = new FileOutputStream(currentLogFile, true)) {
            fos.write(logData.getBytes());
            currentFileSize += logData.length();
        }
    }

    // Пример триггера для записи события
    public void onEvent(String eventType, String message) throws IOException {
        String header = createHeader(eventType);
        LogEntity logEntity = new LogEntity(header, message);
        log(logEntity);
    }

    // Пример создания заголовка (здесь можно добавить логику для created, mistake, success)
    private String createHeader(String eventType) {
        String header = "";
        if ("created".equals(eventType)) {
            header = "CREATED";
        } else if ("mistake".equals(eventType)) {
            header = "MISTAKE";
        } else if ("success".equals(eventType)) {
            header = "SUCCESS";
        }
        return header;
    }
}

