package diploma_validator;

import console.Console;
import console.ConsoleManager;
import controller.DiplomaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.impl.StudentRepositoryJPA;
import service.StudentService;
import validator.StudentValidator;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
@EntityScan(basePackages = "repository.entity")
@EnableJpaRepositories(basePackages = "repository.impl")
@ComponentScan("controller")
@ComponentScan("validator")
public class DiplomaValidatorApp implements CommandLineRunner {

    @Autowired
    private StudentRepositoryJPA studentRepositoryJPA;

    @Autowired
    private StudentValidator studentValidator;

    @Autowired
    private DiplomaController diplomaController;


    public static void main(String[] args) {
        SpringApplication.run(DiplomaValidatorApp.class, args);
    }

    //todo: property not manual
    @Bean
    public Properties applicationProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(DiplomaValidatorApp.class.getClassLoader().getResourceAsStream("application.properties"));
        return properties;
    }





    //TODO: not inject bean in Application class
    @Bean
    public StudentService studentService(StudentRepositoryJPA studentRepositoryJPA, StudentValidator studentValidator) {
        return new StudentService(studentRepositoryJPA, studentValidator);
    }

    //TODO: not inject bean in Application class
    @Bean
    public Console console() {
        return new Console();
    }

    @Bean
    public ConsoleManager consoleManager(StudentService studentService, Console console) {
        return new ConsoleManager(studentService, console);
    }




    @Override
    public void run(String... args) throws Exception {
        ConsoleManager consoleManager = consoleManager(studentService(studentRepositoryJPA, studentValidator), console());
        consoleManager.start();
        diplomaController.start();
    }



}
