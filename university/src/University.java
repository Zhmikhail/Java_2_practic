import controller.ExternalDataController;
import repository.impl.UniversityRepositoryImpl;
import service.ExternalDataService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class University {

    public static void main(String[] args) {
        int externalPort = 0;

        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("resources/config/application.properties")) {
            properties.load(fis);
            externalPort = Integer.parseInt(properties.getProperty("externalProcessor.port")); // Пример с портом
        } catch (IOException e) {
            System.err.println("Failed to load properties file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        UniversityRepositoryImpl universityRepository = new UniversityRepositoryImpl();
        ExternalDataService externalDataService = new ExternalDataService(universityRepository);
        ExternalDataController controller = new ExternalDataController(externalDataService);

        controller.startServer(externalPort);
    }
}
