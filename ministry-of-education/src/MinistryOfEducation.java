import controller.MinistryController;
import repository.impl.UniversityRepositoryImpl;
import service.MinistryService;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MinistryOfEducation {

    public static void main(String[] args) {
        int internalPort = 0;

        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("resources/config/application.properties")) {
            properties.load(fis);
            internalPort = Integer.parseInt(properties.getProperty("internalProcessor.port")); // Пример с портом
        } catch (IOException e) {
            System.err.println("Failed to load properties file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        UniversityRepositoryImpl universityRepository = new UniversityRepositoryImpl();
        MinistryService ministryService = new MinistryService(universityRepository);
        MinistryController controller = new MinistryController(ministryService);

        controller.startServer(internalPort);
    }
}
