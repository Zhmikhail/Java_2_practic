package mapper;

import repository.entity.StudentEntity;
import transport.dto.request.StudentRequestDto;

public class StudentMapper {

    public static StudentEntity toEntity(String name, int age, String university, String specialtyCode, int diplomaNumber) {
        StudentEntity student = new StudentEntity();
        student.setName(name);
        student.setAge(age);
        student.setUniversity(university);
        student.setSpecialtyCode(specialtyCode);
        student.setDiplomaNumber(diplomaNumber);
        return student;
    }

    public static StudentRequestDto toDto(StudentEntity student) {
        StudentRequestDto request = new StudentRequestDto();
        request.setName(student.getName());
        request.setAge(student.getAge());
        request.setUniversity(student.getUniversity());
        request.setSpecialtyCode(student.getSpecialtyCode());
        request.setDiplomaNumber(student.getDiplomaNumber());
        return request;
    }
}
