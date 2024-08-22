package service;

import exception.ValidationException;
import exception.ConnectException;
import exception.CheckException;
import mapper.StudentMapper;
import repository.StudentRepository;
import repository.entity.StudentEntity;
import transport.client.SocketClient;
import transport.dto.request.StudentRequest;
import transport.dto.response.ValidationResponse;

import java.io.IOException;
import java.util.UUID;

public class StudentService {
    private final StudentRepository studentRepository;
    private final SocketClient internalProcessorClient;
    private final SocketClient externalProcessorClient;

    public StudentService(StudentRepository studentRepository, SocketClient internalProcessorClient, SocketClient externalProcessorClient) {
        this.studentRepository = studentRepository;
        this.internalProcessorClient = internalProcessorClient;
        this.externalProcessorClient = externalProcessorClient;
    }

    private void validateAge(int age) throws ValidationException {
        if (age < 18) {
            throw new ValidationException("Age cannot be less than 18");
        }
    }

    public void processStudentData(String name, int age, String university, String specialtyCode, int diplomaNumber) throws ValidationException, ConnectException, CheckException {
        // место + Сама валидация только по цифре а не по дате
        validateAge(age);
        // маппинг в маппере
        StudentEntity student = new StudentEntity();
        //рандом
        student.setId(UUID.randomUUID().toString());
        student.setName(name);
        student.setAge(age);
        student.setUniversity(university);
        student.setSpecialtyCode(specialtyCode);
        student.setDiplomaNumber(diplomaNumber);

        studentRepository.save(student);

        StudentRequest request = StudentMapper.toDto(student);
        // в отддельный класс
        try {
            ValidationResponse internalResponse = internalProcessorClient.sendData(request);
            if (!internalResponse.isValid()) {
                throw new CheckException("Invalid university or specialty");
            }

            ValidationResponse externalResponse = externalProcessorClient.sendData(request);
            if (!externalResponse.isValid()) {
                throw new CheckException("Invalid student data");
            }
        } catch (IOException e) {
            throw new ConnectException("Error communicating with data processor");
        }

        studentRepository.update(student);
    }
}
