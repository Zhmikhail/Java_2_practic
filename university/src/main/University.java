package main;

import controlleruniversity.UniversityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import repository.impl.UniversityRepositoryImpl;
import service.UniversityService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@EntityScan(basePackages = "repository.entity") // Сканируем пакет с Entity
@ComponentScan(basePackages = "controlleruniversity")
@EnableMongoRepositories(basePackages = "repository.impl") // Включаем поддержку MongoDB репозиториев
public class University implements CommandLineRunner {

    @Autowired
    private UniversityRepositoryImpl universityRepository;

    @Autowired
    private UniversityService universityService;

    @Autowired // Добавлено: автоматическое связывание контроллера
    private UniversityController universityController;

    public static void main(String[] args) {
        SpringApplication.run(University.class, args);
    }

    @Bean
    public UniversityService universityService(UniversityRepositoryImpl universityRepository) {
        return new UniversityService(universityRepository);
    }

    @Override
    public void run(String... args) {
        universityController.start(); // Теперь используется правильный экземпляр контроллера
    }


}
