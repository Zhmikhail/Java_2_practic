package service;

import exception.ValidationException;
import repository.UniversityRepository;
import repository.entity.UniversityEntity;
import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;
import java.util.Optional;

public class InternalDataService {
    private final UniversityRepository universityRepository;

    public InternalDataService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public ValidationResponseDto validateStudentData(StudentRequestDto request) throws ValidationException {
        Optional<UniversityEntity> universityEntityOpt = universityRepository.findByName(request.getUniversity());
        if (!universityEntityOpt.isPresent()) {
            return new ValidationResponseDto(false, "University not found");
        }

        UniversityEntity universityEntity = universityEntityOpt.get();
        boolean specialtyExists = universityEntity.getTrends().stream()
                .anyMatch(trend -> trend.getTrend().equals(request.getSpecialtyCode()));
        if (!specialtyExists) {
            return new ValidationResponseDto(false, "Specialty code not found");
        }

        return new ValidationResponseDto(true, "Internal validation passed");
    }
}
