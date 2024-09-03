import controller.ExternalDataController;
import repository.impl.UniversityRepositoryImpl;
import service.ExternalDataService;

public class Main {

    public static void main(String[] args) {
        UniversityRepositoryImpl universityRepository = new UniversityRepositoryImpl();
        ExternalDataService ExternalDataService = new ExternalDataService(universityRepository);
        ExternalDataController controller = new ExternalDataController(ExternalDataService);
        //FIXME: считывать из пропертей
        controller.startServer(8082);
    }
}
