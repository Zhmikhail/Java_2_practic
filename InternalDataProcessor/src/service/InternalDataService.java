package service;

import exception.ValidationException;
import repository.UniversityRepository;
import repository.entity.UniversityEntity;
import transport.dto.request.StudentRequest;
import transport.dto.response.ValidationResponse;
import java.util.Optional;

public class InternalDataService {
    private final UniversityRepository universityRepository;

    public InternalDataService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public ValidationResponse validateStudentData(StudentRequest request) throws ValidationException {
        Optional<UniversityEntity> universityEntityOpt = universityRepository.findByName(request.getUniversity());
        if (!universityEntityOpt.isPresent()) {
            return new ValidationResponse(false, "University not found");
        }

        UniversityEntity universityEntity = universityEntityOpt.get();
        boolean specialtyExists = universityEntity.getTrends().stream()
                .anyMatch(trend -> trend.getTrend().equals(request.getSpecialtyCode()));
        if (!specialtyExists) {
            return new ValidationResponse(false, "Specialty code not found");
        }

        return new ValidationResponse(true, "Internal validation passed");
    }
}
