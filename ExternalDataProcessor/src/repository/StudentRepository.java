package repository;

import repository.entity.StudentEntity;

public interface StudentRepository {
    StudentEntity findStudent(String university, int diplomaNumber);
}
