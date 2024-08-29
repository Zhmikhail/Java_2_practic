package service;

import exception.ValidationException;
import repository.UniversityRepository;
import repository.entity.UniversityEntity;
import repository.entity.SpecialtyEntity;
import repository.entity.StudentEntity;
import transport.dto.request.StudentRequest;
import transport.dto.response.ValidationResponse;

import java.util.Optional;

public class ExternalDataService {
    private final UniversityRepository universityRepository;

    public ExternalDataService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public ValidationResponse validateStudentData(StudentRequest request) throws ValidationException {
        Optional<UniversityEntity> universityEntityOpt = universityRepository.findByName(request.getUniversity());
        if (!universityEntityOpt.isPresent()) {
            return new ValidationResponse(false, "University not found");
        }

        UniversityEntity universityEntity = universityEntityOpt.get();
        Optional<SpecialtyEntity> specialtyEntityOpt = universityEntity.getSpecialties().stream()
                .filter(specialty -> specialty.getSpecialtyCode().equals(request.getSpecialtyCode()))
                .findFirst();

        if (!specialtyEntityOpt.isPresent()) {
            return new ValidationResponse(false, "Specialty code not found");
        }

        SpecialtyEntity specialtyEntity = specialtyEntityOpt.get();
        boolean studentExists = specialtyEntity.getStudents().stream()
                .anyMatch(student -> student.getName().equals(request.getName()) && student.getDiplomaNumber() == request.getDiplomaNumber());

        if (!studentExists) {
            return new ValidationResponse(false, "Student with specified diploma number not found");
        }

        return new ValidationResponse(true, "External validation passed");
    }
}
