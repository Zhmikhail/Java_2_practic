package service;

import enums.ServiceMessages;
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
        studentRepository.saveStudent(student);

        // Логируйте информацию о сохраненном студенте
        logger.info("Processing student data: " + student.toString()); // Выводит всю информацию о студенте, включая ID

        StudentRequestDto request = StudentMapper.toDto(student);
        validateStudentData(request);

        studentRepository.updateStudent(student);
    }


    private void validateStudentData(StudentRequestDto request) throws ValidationException, ConnectException, CheckException {
        ValidationResponseDto internalResponse = processData(request, internalProcessorClient, ServiceMessages.ERROR_COMMUNICATING_WITH_INTERNAL.getMessage());

        if (!internalResponse.isValid()) {
            ValidationResponseDto externalResponse = processData(request, externalProcessorClient, ServiceMessages.ERROR_COMMUNICATING_WITH_EXTERNAL.getMessage());

            if (!externalResponse.isValid()) {
                throw new CheckException(ServiceMessages.INVALID_STUDENT_DATA.getMessage());
            } else {
                logger.info(ServiceMessages.EXTERNAL_PROCESSOR_RESPONSE.getMessage() + externalResponse.isValid());
            }
        }
    }

    private ValidationResponseDto processData(StudentRequestDto request, SocketClient client, String errorMessage) throws ConnectException {
        try {
            ValidationResponseDto response = client.sendData(request);
            logger.log(Level.INFO, response.isValid() ? "Data processed successfully" : "Data processing failed");
            return response;
        } catch (IOException e) {
            logger.log(Level.SEVERE, errorMessage, e);
            throw new ConnectException(errorMessage);
        }
    }

    public void deleteStudent(Optional<StudentEntity> studentOpt) {
        if (studentOpt.isPresent()) {
            studentRepository.deleteStudent(studentOpt.get());
            logger.info(ServiceMessages.STUDENT_DELETED_SUCCESS.getMessage());
        } else {
            logger.warning(ServiceMessages.STUDENT_NOT_FOUND.getMessage());
        }
    }

    public void deleteStudentByDetails(String name, int age, String university, String specialtyCode, int diplomaNumber) {
        deleteStudent(studentRepository.findByDetails(name, age, university, specialtyCode, diplomaNumber));
    }

    public void deleteStudentById(int id) {
        deleteStudent(studentRepository.findById(id));
    }

    public void updateStudent(Optional<StudentEntity> studentOpt, StudentEntity updatedStudent) throws ValidationException, ConnectException, CheckException {
        if (studentOpt.isPresent()) {
            StudentEntity existingStudent = studentOpt.get();
            updateFields(existingStudent, updatedStudent);
            validateStudentData(StudentMapper.toDto(existingStudent));
            studentRepository.updateStudent(existingStudent);
        } else {
            throw new ValidationException(ServiceMessages.STUDENT_NOT_FOUND.getMessage());
        }
    }

    public void updateStudentByDetails(StudentEntity updatedStudent) throws ValidationException, ConnectException, CheckException {
        updateStudent(studentRepository.findByDetails(
                updatedStudent.getName(),
                updatedStudent.getAge(),
                updatedStudent.getUniversity(),
                updatedStudent.getSpecialtyCode(),
                updatedStudent.getDiplomaNumber()), updatedStudent);
    }

    public void updateStudentById(int id, StudentEntity updatedStudent) throws ValidationException, ConnectException, CheckException {
        updateStudent(studentRepository.findById(id), updatedStudent);
    }

    public Optional<StudentEntity> findByDetails(String name, int age, String university, String specialtyCode, int diplomaNumber) {
        return studentRepository.findByDetails(name, age, university, specialtyCode, diplomaNumber);
    }


    private void updateFields(StudentEntity existingStudent, StudentEntity updatedStudent) {
        existingStudent.setId(existingStudent.getId());
        if (updatedStudent.getName() != null) existingStudent.setName(updatedStudent.getName());
        if (updatedStudent.getAge() != -1) existingStudent.setAge(updatedStudent.getAge());
        if (updatedStudent.getUniversity() != null) existingStudent.setUniversity(updatedStudent.getUniversity());
        if (updatedStudent.getSpecialtyCode() != null) existingStudent.setSpecialtyCode(updatedStudent.getSpecialtyCode());
        if (updatedStudent.getDiplomaNumber() != -1) existingStudent.setDiplomaNumber(updatedStudent.getDiplomaNumber());
    }

    public void processMonitoringData(String header, String message) throws IOException {
        monitoringClient.sendLog(header, message);
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<StudentEntity> findById(int id) {
        return studentRepository.findById(id);
    }
}
