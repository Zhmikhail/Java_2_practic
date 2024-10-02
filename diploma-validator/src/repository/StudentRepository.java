package repository;

import repository.entity.StudentEntity;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    void save(StudentEntity student);
    List<StudentEntity> findAll();
    Optional<StudentEntity> findByDetails(String name, int age, String university, String specialtyCode, int diplomaNumber);
    void update(StudentEntity student);
    void delete(StudentEntity student);
}
