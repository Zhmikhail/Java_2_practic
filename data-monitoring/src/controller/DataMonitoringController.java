package controller;

import service.MonitoringService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class DataMonitoringController {
    private final Logger LOGGER = Logger.getLogger(DataMonitoringController.class.getName());
    private final MonitoringService monitoringService;

    public DataMonitoringController(MonitoringService monitoringService) {
        this.monitoringService=monitoringService;
    }



    public void startServer(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOGGER.info("Monitoring service is start");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(
                             new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8))) {

                    String eventType = reader.readLine();  // Первая строка - тип события
                    String message = reader.readLine();    // Вторая строка - сообщение

                    if (eventType != null && message != null) {
                        System.out.println("Received log: " + eventType + " - " + message);
                        monitoringService.onEvent(eventType, message);  // Запись лога через DataMonitoring
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
