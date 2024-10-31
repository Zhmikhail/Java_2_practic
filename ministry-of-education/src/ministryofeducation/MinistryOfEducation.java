package ministryofeducation;

import controller.MinistryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import repository.impl.UniversityRepositoryImpl;
import service.MinistryService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@EntityScan(basePackages = "repository.entity") // Сканируем пакет с Entity
@EnableMongoRepositories(basePackages = "repository.impl") // Включаем поддержку MongoDB репозиториев
@ComponentScan(basePackages = "controller")
public class MinistryOfEducation implements CommandLineRunner {

    @Autowired
    private UniversityRepositoryImpl universityRepository;

    @Autowired
    private MinistryService ministryService;

    @Autowired
    private MinistryController ministryController;

    public static void main(String[] args) {
        SpringApplication.run(MinistryOfEducation.class, args);
    }

    @Bean
    public MinistryService ministryService(UniversityRepositoryImpl universityRepository) {
        return new MinistryService(universityRepository);
    }

    @Override
    public void run(String... args) {
        ministryController.start();
    }


}
