package repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import repository.entity.UniversityEntity;
import repository.UniversityRepository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

public class UniversityRepositoryImpl implements UniversityRepository {
    private static final String FILE_PATH = "Internal.json";
    private static final Logger logger = Logger.getLogger(UniversityRepositoryImpl.class.getName());

    @Override
    public List<UniversityEntity> findAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Arrays.asList(objectMapper.readValue(new File(FILE_PATH), UniversityEntity[].class));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read universities from JSON file", e);
        }
    }

    @Override
    public Optional<UniversityEntity> findByName(String name) {
        // Чтение всех университетов из файла
        List<UniversityEntity> universities = findAll();
        // Поиск университета по имени
        return universities.stream()
                .filter(university -> university.getUniversity().equalsIgnoreCase(name))
                .findFirst();
    }
}
