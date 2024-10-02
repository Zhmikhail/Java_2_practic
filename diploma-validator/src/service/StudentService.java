package service;

import exception.ValidationException;
import exception.ConnectException;
import exception.CheckException;
import mapper.StudentMapper;
import org.springframework.stereotype.Service;
import repository.StudentRepository;
import repository.entity.StudentEntity;
import transport.client.SocketClient;
import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StudentService {

    private static final Logger logger = Logger.getLogger(StudentService.class.getName());

    private final StudentRepository studentRepository;
    private final SocketClient internalProcessorClient;
    private final SocketClient externalProcessorClient;
    private final SocketClient monitoringClient;

    public StudentService(StudentRepository studentRepository, SocketClient internalProcessorClient, SocketClient externalProcessorClient,
                          SocketClient monitoringClient) {
        this.studentRepository = studentRepository;
        this.internalProcessorClient = internalProcessorClient;
        this.externalProcessorClient = externalProcessorClient;
        this.monitoringClient = monitoringClient;
    }

    public void processStudentData(String name, int age, String university, String specialtyCode, int diplomaNumber) throws ValidationException, ConnectException, CheckException {
        StudentEntity student = StudentMapper.toEntity(name, age, university, specialtyCode, diplomaNumber);
        studentRepository.save(student);

        StudentRequestDto request = StudentMapper.toDto(student);

        ValidationResponseDto internalResponse = processInternalData(request);
        if (!internalResponse.isValid()) {
            System.out.println(ServiceMessages.INVALID_UNIVERSITY_OR_SPECIALTY.getMessage());

            ValidationResponseDto externalResponse = processExternalData(request);

            if (!externalResponse.isValid()) {
                throw new CheckException(ServiceMessages.INVALID_STUDENT_DATA.getMessage());
            } else {
                System.out.println(ServiceMessages.EXTERNAL_PROCESSOR_RESPONSE.getMessage() + externalResponse.isValid());
            }
        } else {
            ValidationResponseDto externalResponse = processExternalData(request);

            if (!externalResponse.isValid()) {
                throw new CheckException(ServiceMessages.INVALID_STUDENT_DATA.getMessage());
            }
        }

        studentRepository.update(student);
    }

    private ValidationResponseDto processInternalData(StudentRequestDto request) throws ConnectException {
        try {
            ValidationResponseDto response = internalProcessorClient.sendData(request);
            logger.log(Level.INFO, ServiceMessages.INTERNAL_PROCESSOR_RESPONSE.getMessage() + response.isValid());
            return response;
        } catch (IOException e) {
            logger.log(Level.WARNING, ServiceMessages.ERROR_COMMUNICATING_WITH_INTERNAL.getMessage(), e);
            throw new ConnectException(ServiceMessages.ERROR_COMMUNICATING_WITH_INTERNAL.getMessage());
        }
    }

    private ValidationResponseDto processExternalData(StudentRequestDto request) throws ConnectException {
        try {
            ValidationResponseDto response = externalProcessorClient.sendData(request);
            logger.log(Level.INFO, ServiceMessages.EXTERNAL_PROCESSOR_RESPONSE.getMessage() + response.isValid());
            return response;
        } catch (IOException e) {
            logger.log(Level.SEVERE, ServiceMessages.ERROR_COMMUNICATING_WITH_EXTERNAL.getMessage(), e);
            throw new ConnectException(ServiceMessages.ERROR_COMMUNICATING_WITH_EXTERNAL.getMessage());
        }
    }

    public void processMonitoringData(String header, String message) throws IOException {
        monitoringClient.sendLog(header, message);
    }

    public void deleteStudent(String name, int age, String university, String specialtyCode, int diplomaNumber) {
        Optional<StudentEntity> studentOpt = studentRepository.findByDetails(name, age, university, specialtyCode, diplomaNumber);
        if (studentOpt.isPresent()) {
            studentRepository.delete(studentOpt.get());
            logger.info(ServiceMessages.STUDENT_DELETED_SUCCESS.getMessage());
        } else {
            logger.warning(ServiceMessages.STUDENT_NOT_FOUND.getMessage());
        }
    }

    public void updateStudent(StudentEntity student) throws ValidationException, ConnectException, CheckException {
        ValidationResponseDto internalResponse = processInternalData(StudentMapper.toDto(student));

        if (internalResponse.isValid()) {
            studentRepository.update(student);
            logger.info(ServiceMessages.STUDENT_UPDATED_SUCCESS.getMessage());
        } else {
            throw new ValidationException(ServiceMessages.INVALID_UNIVERSITY_OR_SPECIALTY.getMessage());
        }
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }
}
