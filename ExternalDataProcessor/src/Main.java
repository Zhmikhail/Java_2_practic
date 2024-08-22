//package src;

import controller.ExternalDataProcessorController;
import repository.impl.StudentRepositoryImpl;
import service.StudentService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Инициализация слоев репозитория и сервиса
        StudentRepositoryImpl universityRepository = new StudentRepositoryImpl();
        StudentService externalDataService = new StudentService(universityRepository);
        ExternalDataProcessorController controller = new ExternalDataProcessorController(externalDataService);

        // Запуск сервера
        controller.startServer(8082);
    }
}
