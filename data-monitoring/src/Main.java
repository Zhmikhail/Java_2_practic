//package src;

import controller.DataMonitoringController;
import service.MonitoringService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
            properties.load(new FileInputStream("resources/config/application.properties"));

        int monitoringPort = Integer.parseInt(properties.getProperty("monitoring.port"));
        MonitoringService monitoringService = new MonitoringService(properties);
        DataMonitoringController controller = new DataMonitoringController(monitoringService);

        // Запуск сервера
        controller.startServer(monitoringPort);
    }
}
