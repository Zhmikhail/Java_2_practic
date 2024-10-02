package service;

import exception.ValidationException;
import repository.UniversityRepository;
import repository.entity.UniversityEntity;
import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.Optional;

public class MinistryService {
    private final UniversityRepository universityRepository;
    private static final Logger logger = Logger.getLogger(MinistryService.class.getName());


    public MinistryService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public ValidationResponseDto validateStudentData(StudentRequestDto request) throws ValidationException {
        Optional<UniversityEntity> universityEntityOpt = universityRepository.findByName(request.getUniversity());
        if (!universityEntityOpt.isPresent()) {
            logger.log(Level.INFO, MinistryValidationMessages.UNIVERSITY_NOT_FOUND.getMessage());
            return new ValidationResponseDto(false, MinistryValidationMessages.UNIVERSITY_NOT_FOUND.getMessage());
        }

        UniversityEntity universityEntity = universityEntityOpt.get();
        boolean specialtyExists = universityEntity.getTrends().stream()
                .anyMatch(trend -> trend.getTrend().equals(request.getSpecialtyCode()));

        if (!specialtyExists) {
            logger.log(Level.INFO, MinistryValidationMessages.SPECIALTY_NOT_FOUND.getMessage());
            return new ValidationResponseDto(false, MinistryValidationMessages.SPECIALTY_NOT_FOUND.getMessage());
        }

        logger.log(Level.INFO, MinistryValidationMessages.VALIDATION_PASSED.getMessage());
        return new ValidationResponseDto(true, MinistryValidationMessages.VALIDATION_PASSED.getMessage());
    }
}
