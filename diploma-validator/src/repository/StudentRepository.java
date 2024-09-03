package repository;

import repository.entity.StudentEntity;
import java.util.List;

public interface StudentRepository {
    void save(StudentEntity student);
    List<StudentEntity> findAll();
    void update(StudentEntity student);
}
