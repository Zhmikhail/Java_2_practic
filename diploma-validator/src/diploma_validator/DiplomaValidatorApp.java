package diploma_validator;

import com.mongodb.client.MongoClients;
import console.Console;
import console.ConsoleManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import repository.StudentRepository;
import repository.impl.StudentRepositoryImpl;
import service.StudentService;
import transport.client.SocketClient;
import transport.client.impl.SocketClientImpl;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class DiplomaValidatorApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DiplomaValidatorApp.class, args);
    }

    @Bean
    public Properties applicationProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(DiplomaValidatorApp.class.getClassLoader().getResourceAsStream("application.properties"));
        return properties;
    }


    @Bean
    public SocketClient internalProcessorClient(Properties properties) {
        String host = properties.getProperty("internalProcessor.host");
        int port = Integer.parseInt(properties.getProperty("internalProcessor.port"));
        return new SocketClientImpl(host, port);
    }

    @Bean
    public SocketClient externalProcessorClient(Properties properties) {
        String host = properties.getProperty("externalProcessor.host");
        int port = Integer.parseInt(properties.getProperty("externalProcessor.port"));
        return new SocketClientImpl(host, port);
    }

    @Bean
    public SocketClient monitoringClient(Properties properties) {
        String host = properties.getProperty("monitoring.host");
        int port = Integer.parseInt(properties.getProperty("monitoring.port"));
        return new SocketClientImpl(host, port);
    }

    @Bean
    public StudentRepository studentRepository(org.springframework.data.mongodb.core.MongoTemplate mongoTemplate) {
        return new StudentRepositoryImpl(mongoTemplate);
    }

    @Bean
    public StudentService studentService(StudentRepository studentRepository,
                                         SocketClient internalProcessorClient,
                                         SocketClient externalProcessorClient,
                                         SocketClient monitoringClient) {
        return new StudentService(studentRepository, internalProcessorClient, externalProcessorClient, monitoringClient);
    }

    @Bean
    public Console console() {
        return new Console();
    }

    @Bean
    public ConsoleManager consoleManager(StudentService studentService, Console console) {
        return new ConsoleManager(studentService, console);
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(Properties properties) {
        String connectionString = properties.getProperty("spring.data.mongodb.uri");
        return new SimpleMongoClientDatabaseFactory(MongoClients.create(connectionString), "diploma-validator");
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }

    @Override
    public void run(String... args) throws Exception {
        ConsoleManager consoleManager = consoleManager(studentService(
                studentRepository(mongoTemplate(mongoDatabaseFactory(applicationProperties()))),
                internalProcessorClient(applicationProperties()),
                externalProcessorClient(applicationProperties()),
                monitoringClient(applicationProperties())
        ), console());
        consoleManager.start();
    }
}
