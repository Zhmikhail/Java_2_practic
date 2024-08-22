package repository.impl;

import repository.StudentRepository;
import repository.entity.StudentEntity;
import utils.CSVUtils;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private static final String CSV_FILE = "students.csv";

    @Override
    public void save(StudentEntity student) {
        CSVUtils.writeToCSV(CSV_FILE, student);
    }

    @Override
    public List<StudentEntity> findAll() {
        return CSVUtils.readFromCSV(CSV_FILE);
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
        CSVUtils.writeAllToCSV(CSV_FILE, students);
    }
}
