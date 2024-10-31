package controller;

import exception.CheckException;
import exception.ConnectException;
import exception.ValidationException;
import mapper.StudentMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import repository.entity.StudentEntity;
import service.StudentService;
import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;


import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("diploma")
public class DiplomaController {
private final StudentService studentService;
private final RestTemplate restTemplate = new RestTemplate();

private final String internalUrl = "http://localhost:8055/internal/validateStudentData" ;
private final String externalUrl = "http://localhost:8088/external/validateStudentData";

    public DiplomaController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<StudentEntity> getStudent(@PathVariable int id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody StudentEntity studentEntity) throws CheckException, ConnectException {
        try  {
            studentService.addStudent(studentEntity);
            StudentRequestDto request = StudentMapper.toDto(studentEntity);
            validateStudentData(request);
        }
        catch (CheckException e){
            return ResponseEntity.ok(e.getMessage());
        } catch (ValidationException e) {
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok("Студент успешно добавлен");

    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody StudentEntity studentEntity) throws ValidationException, CheckException, ConnectException {
        studentService.updateStudentById(id, studentEntity);
        return ResponseEntity.ok("Студент обновлен");

    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        try {
            studentService.deleteStudentById(id);
            return ResponseEntity.ok("Студент удален");
        }
        catch (Exception e){
            return ResponseEntity.ok("Студент не найден");
        }
    }


    public ResponseEntity<String> validateStudentData(StudentRequestDto request) throws CheckException, ConnectException {
        ValidationResponseDto internalResponse = processData(request, internalUrl, "Ошибка связи с внутренним процессором");

        if (internalResponse.isValid()) {
            ValidationResponseDto externalResponse = processData(request, externalUrl, "Ошибка связи с внешним процессором");

            if (!externalResponse.isValid()) {
                throw new CheckException(externalResponse.getMessage());
            } else {
                return ResponseEntity.ok("Данные студента прошли внешнюю валидацию");
            }
        } else {
            throw new CheckException(internalResponse.getMessage());
        }
    }

    private ValidationResponseDto processData(StudentRequestDto request, String url, String errorMessage) throws ConnectException {
        try {
            ResponseEntity<ValidationResponseDto> response = restTemplate.postForEntity(url, request, ValidationResponseDto.class);
            System.out.println("Received response from " + url + ": " + response.getBody());
            return response.getBody();
        } catch (RestClientException e) {
            throw new ConnectException(errorMessage);
        }
    }


    public void start() {
        System.out.println("DiplomaController started");
    }
}
