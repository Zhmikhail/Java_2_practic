package repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repository.entity.StudentEntity;

import java.util.Optional;

@Repository
public interface StudentRepositoryJPA extends JpaRepository<StudentEntity, Integer> {
    Optional<StudentEntity> findByNameAndAgeAndUniversityAndSpecialtyCodeAndDiplomaNumber(
            String name, int age, String university, String specialtyCode, int diplomaNumber);
}

