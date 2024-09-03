package repository.impl;

import repository.StudentRepository;
import repository.entity.StudentEntity;
import utils.CSVUtils;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private final String csvFilePath;

    public StudentRepositoryImpl(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    @Override
    public void save(StudentEntity student) {
        CSVUtils.writeToCSV(csvFilePath, student);
    }

    @Override
    public List<StudentEntity> findAll() {
        return CSVUtils.readFromCSV(csvFilePath);
    }

    @Override
    public void update(StudentEntity student) {
        List<StudentEntity> students = findAll();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(student.getId())) {
                students.set(i, student);
                break;
            }
        }
        CSVUtils.writeAllToCSV(csvFilePath, students);
    }
}
