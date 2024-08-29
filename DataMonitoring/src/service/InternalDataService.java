package service;

import exception.ValidationException;
import repository.UniversityRepository;
import repository.entity.UniversityEntity;
import transport.dto.request.StudentRequest;
import transport.dto.response.ValidationResponse;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.Optional;

public class InternalDataService {
    private final UniversityRepository universityRepository;
    private static final Logger logger = Logger.getLogger(InternalDataService.class.getName());

    public InternalDataService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
        logger.log(Level.INFO, "Starting myMethod4");
    }

    public ValidationResponse validateStudentData(StudentRequest request) throws ValidationException {
        Optional<UniversityEntity> universityEntityOpt = universityRepository.findByName(request.getUniversity());
        logger.log(Level.INFO, "Starting myMethod0");
        if (!universityEntityOpt.isPresent()) {
            return new ValidationResponse(false, "University not found");
        }

        UniversityEntity universityEntity = universityEntityOpt.get();
        logger.log(Level.INFO, "Starting myMethod5");
        boolean specialtyExists = universityEntity.getTrends().stream()
                .anyMatch(trend -> trend.getTrend().equals(request.getSpecialtyCode()));
        logger.log(Level.INFO, "Starting myMethod1");
        if (!specialtyExists) {
            return new ValidationResponse(false, "Specialty code not found");
        }

        return new ValidationResponse(true, "Internal validation passed");
    }
}
