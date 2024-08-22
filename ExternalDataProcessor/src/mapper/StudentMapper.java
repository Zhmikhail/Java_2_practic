package mapper;

import transport.dto.response.ValidationResponse;
import repository.entity.StudentEntity;

public class StudentMapper {
    public static ValidationResponse toResponse(StudentEntity student, boolean isValid) {
        return new ValidationResponse(isValid, isValid ? "Validation successful" : "Validation failed");
    }
}