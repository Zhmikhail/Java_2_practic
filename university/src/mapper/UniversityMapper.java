package mapper;

import transport.dto.response.ValidationResponseDto;
import repository.entity.StudentEntity;
import transport.dto.request.StudentRequestDto;

public class UniversityMapper {
    //FIXME: не совсем верное использование шаблона адаптер (маппер)
    public static ValidationResponseDto toValidationResponse(boolean isValid, String message) {
        return new ValidationResponseDto(isValid, message);
    }

    public static StudentRequestDto toDto(StudentEntity studentEntity, String university, String specialtyCode) {
        StudentRequestDto dto = new StudentRequestDto();
        dto.setName(studentEntity.getName());
        dto.setUniversity(university);
        dto.setSpecialtyCode(specialtyCode);
        dto.setDiplomaNumber(studentEntity.getDiplomaNumber());
        return dto;
    }

    public static StudentEntity toEntity(StudentRequestDto studentRequestDto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(studentRequestDto.getName());
        studentEntity.setDiplomaNumber(studentRequestDto.getDiplomaNumber());
        return studentEntity;
    }


}
