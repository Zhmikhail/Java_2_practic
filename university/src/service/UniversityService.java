package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UniversityRepository;
import repository.entity.UniversityEntity;
import repository.entity.SpecialtyEntity;
import repository.impl.UniversityRepositoryImpl;
import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UniversityService {

    private final UniversityRepositoryImpl universityRepository;
    private static final Logger logger = Logger.getLogger(UniversityService.class.getName());

    @Autowired
    public UniversityService(UniversityRepositoryImpl universityRepository) {
        this.universityRepository = universityRepository;
    }

    public ValidationResponseDto validateStudentData(StudentRequestDto request) {
        // Поиск университета по названию
        Optional<UniversityEntity> universityEntityOpt = universityRepository.findByUniversity(request.getUniversity());
        if (!universityEntityOpt.isPresent()) {
            logger.log(Level.INFO, UniversityValidationMessages.UNIVERSITY_NOT_FOUND.getMessage());
            return new ValidationResponseDto(false, UniversityValidationMessages.UNIVERSITY_NOT_FOUND.getMessage());
        }

        UniversityEntity universityEntity = universityEntityOpt.get();

        // Поиск специальности по коду
        Optional<SpecialtyEntity> specialtyEntityOpt = universityEntity.getSpecialties().stream()
                .filter(specialty -> specialty.getSpecialtyCode().equals(request.getSpecialtyCode()))
                .findFirst();

        if (!specialtyEntityOpt.isPresent()) {
            logger.log(Level.INFO, UniversityValidationMessages.SPECIALTY_NOT_FOUND.getMessage());
            return new ValidationResponseDto(false, UniversityValidationMessages.SPECIALTY_NOT_FOUND.getMessage());
        }

        SpecialtyEntity specialtyEntity = specialtyEntityOpt.get();

        // Проверка на наличие студента в списке студентов специальности
        boolean studentExists = specialtyEntity.getStudents().stream()
                .anyMatch(student -> student.getName().equals(request.getName()) &&
                        student.getDiplomaNumber() == request.getDiplomaNumber());

        if (!studentExists) {
            logger.log(Level.INFO, UniversityValidationMessages.STUDENT_NOT_FOUND.getMessage());
            return new ValidationResponseDto(false, UniversityValidationMessages.STUDENT_NOT_FOUND.getMessage());
        }

        logger.log(Level.INFO, UniversityValidationMessages.VALIDATION_PASSED.getMessage());
        return new ValidationResponseDto(true, UniversityValidationMessages.VALIDATION_PASSED.getMessage());
    }
}
