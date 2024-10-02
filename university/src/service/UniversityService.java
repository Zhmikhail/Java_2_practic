package service;

import exception.ValidationException;
import repository.UniversityRepository;
import repository.entity.UniversityEntity;
import repository.entity.SpecialtyEntity;
import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.Optional;

public class UniversityService {
    private final UniversityRepository universityRepository;
    private static final Logger logger = Logger.getLogger(UniversityService.class.getName());


    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public ValidationResponseDto validateStudentData(StudentRequestDto request) throws ValidationException {
        Optional<UniversityEntity> universityEntityOpt = universityRepository.findByName(request.getUniversity());
        if (!universityEntityOpt.isPresent()) {
            logger.log(Level.INFO, UniversityValidationMessages.UNIVERSITY_NOT_FOUND.getMessage());
            return new ValidationResponseDto(false, UniversityValidationMessages.UNIVERSITY_NOT_FOUND.getMessage());
        }

        UniversityEntity universityEntity = universityEntityOpt.get();
        Optional<SpecialtyEntity> specialtyEntityOpt = universityEntity.getSpecialties().stream()
                .filter(specialty -> specialty.getSpecialtyCode().equals(request.getSpecialtyCode()))
                .findFirst();

        if (!specialtyEntityOpt.isPresent()) {
            logger.log(Level.INFO, UniversityValidationMessages.SPECIALTY_NOT_FOUND.getMessage());
            return new ValidationResponseDto(false, UniversityValidationMessages.SPECIALTY_NOT_FOUND.getMessage());
        }

        SpecialtyEntity specialtyEntity = specialtyEntityOpt.get();
        boolean studentExists = specialtyEntity.getStudents().stream()
                .anyMatch(student -> student.getName().equals(request.getName()) && student.getDiplomaNumber() == request.getDiplomaNumber());

        if (!studentExists) {
            logger.log(Level.INFO, UniversityValidationMessages.STUDENT_NOT_FOUND.getMessage());
            return new ValidationResponseDto(false, UniversityValidationMessages.STUDENT_NOT_FOUND.getMessage());
        }

        logger.log(Level.INFO, UniversityValidationMessages.VALIDATION_PASSED.getMessage());
        return new ValidationResponseDto(true, UniversityValidationMessages.VALIDATION_PASSED.getMessage());
    }
}
