import controller.InternalDataController;
import repository.impl.UniversityRepositoryImpl;
import service.InternalDataService;

public class Main {

    public static void main(String[] args) {
        UniversityRepositoryImpl universityRepository = new UniversityRepositoryImpl();
        InternalDataService internalDataService = new InternalDataService(universityRepository);
        InternalDataController controller = new InternalDataController(internalDataService);

        controller.startServer(8081);
    }
}
