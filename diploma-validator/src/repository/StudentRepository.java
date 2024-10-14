package repository;

import repository.entity.StudentEntity;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    void saveStudent(StudentEntity student);
    List<StudentEntity> getAllStudents();
    void updateStudent(StudentEntity student);
    void updateStudentByDetails(StudentEntity student);
    void deleteStudent(StudentEntity student);
    void deleteStudentByDetails(StudentEntity student);
    Optional<StudentEntity> findByDetails(String name, int age, String university, String specialtyCode, int diplomaNumber);
    Optional<StudentEntity> findById(int id);
    List<StudentEntity> findAll();
}
