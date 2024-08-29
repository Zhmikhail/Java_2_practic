package mapper;

import repository.entity.StudentEntity;
import transport.dto.request.StudentRequest;
import java.util.UUID;

public class StudentMapper {

    public static StudentEntity toEntity(StudentRequest request) {
        StudentEntity student = new StudentEntity();
        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setUniversity(request.getUniversity());
        student.setSpecialtyCode(request.getSpecialtyCode());
        student.setDiplomaNumber(request.getDiplomaNumber());
        return student;
    }

    public static StudentEntity toEntity(String name, int age, String university, String specialtyCode, int diplomaNumber) {
        StudentEntity student = new StudentEntity();
        student.setId(UUID.randomUUID().toString());
        student.setName(name);
        student.setAge(age);
        student.setUniversity(university);
        student.setSpecialtyCode(specialtyCode);
        student.setDiplomaNumber(diplomaNumber);
        return student;
    }

    public static StudentRequest toDto(StudentEntity student) {
        StudentRequest request = new StudentRequest();
        request.setName(student.getName());
        request.setAge(student.getAge());
        request.setUniversity(student.getUniversity());
        request.setSpecialtyCode(student.getSpecialtyCode());
        request.setDiplomaNumber(student.getDiplomaNumber());
        return request;
    }
}
