package service;

import exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import repository.entity.UniversityEntity;
import repository.impl.UniversityRepositoryImpl;
import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.Optional;

@Service
public class MinistryService {

    private final UniversityRepositoryImpl universityRepository;
    private static final Logger logger = Logger.getLogger(MinistryService.class.getName());

    @Autowired
    public MinistryService(UniversityRepositoryImpl universityRepository) {
        this.universityRepository = universityRepository;
    }

    public ValidationResponseDto validateStudentData(StudentRequestDto request) throws ValidationException {
        System.out.println(universityRepository.findAll());
        Optional<UniversityEntity> universityEntityOpt = universityRepository.findByUniversity(request.getUniversity());
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

    public List<UniversityEntity> getAll(){
        return universityRepository.findAll();
    }
}
