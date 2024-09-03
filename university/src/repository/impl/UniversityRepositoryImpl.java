package repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import repository.entity.UniversityEntity;
import repository.UniversityRepository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.io.FileInputStream;
import java.util.Properties;

public class UniversityRepositoryImpl implements UniversityRepository {
    private final String filePath;

    public UniversityRepositoryImpl() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("resources/config/application.properties")) {
            properties.load(fis);
            filePath = properties.getProperty("external.file.path");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties from application.properties", e);
        }
    }

    @Override
    public List<UniversityEntity> findAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Arrays.asList(objectMapper.readValue(new File(filePath), UniversityEntity[].class));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read universities from JSON file", e);
        }
    }

    @Override
    public Optional<UniversityEntity> findByName(String name) {
        List<UniversityEntity> universities = findAll();
        return universities.stream()
                .filter(university -> university.getUniversity().equalsIgnoreCase(name))
                .findFirst();
    }
}
