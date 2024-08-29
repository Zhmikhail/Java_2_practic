//package src;

import controller.InternalDataController;
import repository.impl.UniversityRepositoryImpl;
import service.InternalDataService;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // Инициализация слоев репозитория и сервиса
        logger.log(Level.INFO, "Starting myMethod1");
        UniversityRepositoryImpl universityRepository = new UniversityRepositoryImpl();
        InternalDataService internalDataService = new InternalDataService(universityRepository);
        InternalDataController controller = new InternalDataController(internalDataService);

        // Запуск сервера
        controller.startServer(8081);
    }
}
