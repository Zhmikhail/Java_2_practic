package service;

import exception.ValidationException;
import repository.UniversityRepository;
import repository.entity.UniversityEntity;
import repository.entity.SpecialtyEntity;
import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;

import java.util.Optional;

public class ExternalDataService {
    private final UniversityRepository universityRepository;

    public ExternalDataService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public ValidationResponseDto validateStudentData(StudentRequestDto request) throws ValidationException {
        Optional<UniversityEntity> universityEntityOpt = universityRepository.findByName(request.getUniversity());
        if (!universityEntityOpt.isPresent()) {
            return new ValidationResponseDto(false, "University not found");
        }

        UniversityEntity universityEntity = universityEntityOpt.get();
        Optional<SpecialtyEntity> specialtyEntityOpt = universityEntity.getSpecialties().stream()
                .filter(specialty -> specialty.getSpecialtyCode().equals(request.getSpecialtyCode()))
                .findFirst();

        if (!specialtyEntityOpt.isPresent()) {
            return new ValidationResponseDto(false, "Specialty code not found");
        }

        SpecialtyEntity specialtyEntity = specialtyEntityOpt.get();
        boolean studentExists = specialtyEntity.getStudents().stream()
                .anyMatch(student -> student.getName().equals(request.getName()) && student.getDiplomaNumber() == request.getDiplomaNumber());

        if (!studentExists) {
            return new ValidationResponseDto(false, "Student with specified diploma number not found");
        }

        return new ValidationResponseDto(true, "External validation passed");
    }
}
