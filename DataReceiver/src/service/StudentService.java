package service;

import exception.ValidationException;
import exception.ConnectException;
import exception.CheckException;
import mapper.StudentMapper;
import repository.StudentRepository;
import repository.entity.StudentEntity;
import service.handler.DataProcessorHandler;
import transport.dto.request.StudentRequest;
import validator.StudentValidator;

public class StudentService {
    private final StudentRepository studentRepository;
    private final DataProcessorHandler dataProcessorHandler;
    private final StudentValidator studentValidator;

    public StudentService(StudentRepository studentRepository, DataProcessorHandler dataProcessorHandler) {
        this.studentRepository = studentRepository;
        this.dataProcessorHandler = dataProcessorHandler;
        this.studentValidator = new StudentValidator();
    }

    public void processStudentData(String name, int age, String university, String specialtyCode, int diplomaNumber) throws ValidationException, ConnectException, CheckException {
        studentValidator.validateStudentData(name, age, specialtyCode, diplomaNumber);
        StudentEntity student = StudentMapper.toEntity(name, age, university, specialtyCode, diplomaNumber);
        studentRepository.save(student);
        StudentRequest request = StudentMapper.toDto(student);
        dataProcessorHandler.processData(request);

        studentRepository.update(student);
    }
}
