package repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import repository.StudentRepository;
import repository.entity.StudentEntity;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StudentRepositoryImpl implements StudentRepository {
    private Map<String, List<StudentEntity>> universityData;

    public StudentRepositoryImpl() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        universityData = mapper.readValue(new File("External.json"),
                mapper.getTypeFactory().constructMapType(Map.class, String.class, List.class));
    }

    @Override
    public StudentEntity findStudent(String university, int diplomaNumber) {
        List<StudentEntity> students = universityData.get(university);
        if (students != null) {
            return students.stream()
                    .filter(student -> student.getDiplomaNumber() == diplomaNumber)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}
