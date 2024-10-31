package service;

import enums.ServiceMessages;
import exception.ValidationException;
import exception.ConnectException;
import exception.CheckException;
import mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import repository.entity.StudentEntity;
import repository.impl.StudentRepositoryJPA;
import transport.client.SocketClient;
import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;
import validator.StudentValidator;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StudentService {

    private static final Logger logger = Logger.getLogger(StudentService.class.getName());

    private final StudentRepositoryJPA studentRepositoryJPA;
    private final StudentValidator studentValidator;

    @Autowired
    public StudentService(StudentRepositoryJPA studentRepositoryJPA, StudentValidator studentValidator) {
        this.studentRepositoryJPA = studentRepositoryJPA;
        this.studentValidator = studentValidator;
    }


    public void processStudentData(String name, int age, String university, String specialtyCode, int diplomaNumber) throws ValidationException, ConnectException, CheckException {
        StudentEntity student = StudentMapper.toEntity(name, age, university, specialtyCode, diplomaNumber);
        studentRepositoryJPA.save(student);
        StudentRequestDto request = StudentMapper.toDto(student);
//        validateStudentData(request);
    }


//    private void validateStudentData(StudentRequestDto request) throws ConnectException, CheckException {
//        ValidationResponseDto internalResponse = processData(request, internalProcessorClient, ServiceMessages.ERROR_COMMUNICATING_WITH_INTERNAL.getMessage());
//
//        if (internalResponse.isValid()) {
//            ValidationResponseDto externalResponse = processData(request, externalProcessorClient, ServiceMessages.ERROR_COMMUNICATING_WITH_EXTERNAL.getMessage());
//
//            if (!externalResponse.isValid()) {
//                throw new CheckException(ServiceMessages.INVALID_STUDENT_DATA.getMessage());
//            } else {
//                logger.info(ServiceMessages.EXTERNAL_PROCESSOR_RESPONSE.getMessage() + externalResponse.isValid());
//            }
//        }
//    }

//    private ValidationResponseDto processData(StudentRequestDto request, SocketClient client, String errorMessage) throws ConnectException {
//        try {
//            ValidationResponseDto response = client.sendData(request);
//            logger.log(Level.INFO, response.isValid() ? "Data processed successfully" : "Data processing failed");
//            return response;
//        } catch (IOException e) {
//            logger.log(Level.SEVERE, errorMessage, e);
//            throw new ConnectException(errorMessage);
//        }
//    }

    public void deleteStudent(Optional<StudentEntity> studentOpt) {
        if (studentOpt.isPresent()) {
            studentRepositoryJPA.delete(studentOpt.get());
            logger.info(ServiceMessages.STUDENT_DELETED_SUCCESS.getMessage());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ServiceMessages.STUDENT_NOT_FOUND.getMessage());
        }
    }

    public void deleteStudentByDetails(String name, int age, String university, String specialtyCode, int diplomaNumber) {
        deleteStudent(studentRepositoryJPA.findByNameAndAgeAndUniversityAndSpecialtyCodeAndDiplomaNumber
                (name, age, university, specialtyCode, diplomaNumber));
    }

    public void deleteStudentById(int id) {
        deleteStudent(studentRepositoryJPA.findById(id));
    }

    public void updateStudent(Optional<StudentEntity> studentOpt, StudentEntity updatedStudent) throws ValidationException, ConnectException, CheckException {
        if (studentOpt.isPresent()) {
            StudentEntity existingStudent = studentOpt.get();
            updateFields(existingStudent, updatedStudent);
            //validateStudentData(StudentMapper.toDto(existingStudent));
            studentRepositoryJPA.save(existingStudent);
        } else {
            throw new ValidationException(ServiceMessages.STUDENT_NOT_FOUND.getMessage());
        }
    }

    public void updateStudentByDetails(StudentEntity updatedStudent) throws ValidationException, ConnectException, CheckException {
        updateStudent(studentRepositoryJPA.findByNameAndAgeAndUniversityAndSpecialtyCodeAndDiplomaNumber(
                updatedStudent.getName(),
                updatedStudent.getAge(),
                updatedStudent.getUniversity(),
                updatedStudent.getSpecialtyCode(),
                updatedStudent.getDiplomaNumber()), updatedStudent);
    }

    public void updateStudentById(int id, StudentEntity updatedStudent) throws ValidationException, ConnectException, CheckException {
        updateStudent(studentRepositoryJPA.findById(id), updatedStudent);
    }

    public Optional<StudentEntity> findByDetails(String name, int age, String university, String specialtyCode, int diplomaNumber) {
        return studentRepositoryJPA.findByNameAndAgeAndUniversityAndSpecialtyCodeAndDiplomaNumber
                (name, age, university, specialtyCode, diplomaNumber);
    }


    private void updateFields(StudentEntity existStud, StudentEntity updStud) {
        existStud.setId(existStud.getId());
        if (updStud.getName() != null) existStud.setName(updStud.getName());
        if (updStud.getAge() != -1) existStud.setAge(updStud.getAge());
        if (updStud.getUniversity() != null) existStud.setUniversity(updStud.getUniversity());
        if (updStud.getSpecialtyCode() != null) existStud.setSpecialtyCode(updStud.getSpecialtyCode());
        if (updStud.getDiplomaNumber() != -1) existStud.setDiplomaNumber(updStud.getDiplomaNumber());
    }



    public List<StudentEntity> getAllStudents() {
        return studentRepositoryJPA.findAll();
    }

    public Optional<StudentEntity> findById(int id) {
        return studentRepositoryJPA.findById(id);
    }

    public StudentEntity getById(int id){
        return studentRepositoryJPA.findById(id).get();
    }

    public void addStudent(StudentEntity studentEntity) throws CheckException, ConnectException, ValidationException {
        try {
            studentValidator.validateStudentData(studentEntity.getName(),
                studentEntity.getAge(), studentEntity.getUniversity(), studentEntity.getSpecialtyCode(), studentEntity.getDiplomaNumber());
            studentRepositoryJPA.save(studentEntity);
        }
        catch(Exception e){
            throw e;
        }
    }


}
