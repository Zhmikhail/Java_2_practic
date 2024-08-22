package mapper;

import repository.entity.StudentEntity;
import transport.dto.request.StudentRequest;

import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.logging.Level;

public class StudentMapper {
    private static final Logger logger = Logger.getLogger(StudentMapper.class.getName());

    public static StudentEntity toEntity(StudentRequest request) {
        StudentEntity student = new StudentEntity();
        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setUniversity(request.getUniversity());
        student.setSpecialtyCode(request.getSpecialtyCode());
        student.setDiplomaNumber(request.getDiplomaNumber());
        logger.log(Level.INFO, student.getId() + student.getAge() + student.getName() + student.getUniversity() + student.getSpecialtyCode() + student.getDiplomaNumber());
        return student;
    }

    public static StudentRequest toDto(StudentEntity student) {
        StudentRequest request = new StudentRequest();
        request.setName(student.getName());
        request.setAge(student.getAge());
        request.setUniversity(student.getUniversity());
        request.setSpecialtyCode(student.getSpecialtyCode());
        request.setDiplomaNumber(student.getDiplomaNumber());
        logger.log(Level.INFO, student.getName() + " " + student.getAge() + " " + student.getUniversity() + " " + student.getSpecialtyCode() + " " + student.getDiplomaNumber());
        return request;
    }
}
