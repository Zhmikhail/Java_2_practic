package diploma_validator;

import console.Console;
import console.ConsoleManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
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
    public JdbcTemplate jdbcTemplate(org.springframework.jdbc.datasource.DriverManagerDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public StudentRepository studentRepository(JdbcTemplate jdbcTemplate) {
        return new StudentRepositoryImpl(jdbcTemplate);
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
    public SocketClient internalProcessorClient(Properties applicationProperties) {
        return new SocketClientImpl(applicationProperties.getProperty("internalProcessor.host"),
                Integer.parseInt(applicationProperties.getProperty("internalProcessor.port")));
    }

    @Bean
    public SocketClient externalProcessorClient(Properties applicationProperties) {
        return new SocketClientImpl(applicationProperties.getProperty("externalProcessor.host"),
                Integer.parseInt(applicationProperties.getProperty("externalProcessor.port")));
    }

    @Bean
    public SocketClient monitoringClient(Properties applicationProperties) {
        return new SocketClientImpl(applicationProperties.getProperty("monitoring.host"),
                Integer.parseInt(applicationProperties.getProperty("monitoring.port")));
    }

    @Bean
    public org.springframework.jdbc.datasource.DriverManagerDataSource dataSource(Properties applicationProperties) {
        org.springframework.jdbc.datasource.DriverManagerDataSource dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(applicationProperties.getProperty("db.url"));
        dataSource.setUsername(applicationProperties.getProperty("db.username"));
        dataSource.setPassword(applicationProperties.getProperty("db.password"));
        return dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        ConsoleManager consoleManager = consoleManager(studentService(
                studentRepository(jdbcTemplate(null)),
                internalProcessorClient(applicationProperties()),
                externalProcessorClient(applicationProperties()),
                monitoringClient(applicationProperties())
        ), console());
        consoleManager.start();
    }
}
