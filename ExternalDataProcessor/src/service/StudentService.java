package service;

import transport.dto.request.StudentRequest;
import transport.dto.response.ValidationResponse;
import exception.ValidationException;
import mapper.StudentMapper;
import repository.entity.StudentEntity;
import repository.StudentRepository;
import repository.impl.StudentRepositoryImpl;

import java.io.IOException;

public class StudentService {
    private static final StudentRepository studentRepository;

    public StudentService() throws IOException {
        studentRepository = new StudentRepositoryImpl();
    }

    public static ValidationResponse validateStudent(StudentRequest request) throws ValidationException {
        StudentEntity student = studentRepository.findStudent(request.getUniversity(), request.getDiplomaNumber());
        if (student == null) {
            throw new ValidationException("Student not found or invalid diploma number");
        }

        boolean isValid = student.getStudent().equals(request.getStudent());
        return StudentMapper.toResponse(student, isValid);
    }
}
