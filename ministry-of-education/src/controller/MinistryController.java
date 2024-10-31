package controller;

import exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import repository.entity.UniversityEntity;
import service.MinistryService;
import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;

import java.util.List;

@RestController
@RequestMapping("internal")
public class MinistryController {

    private final MinistryService ministryService;
    private final RestTemplate restTemplate = new RestTemplate();

    // Конструктор
    public MinistryController(MinistryService ministryService) {
        this.ministryService = ministryService;
    }

    public ResponseEntity<List<UniversityEntity>> start() {
        System.out.println("Ministry started");
        System.out.println(ResponseEntity.ok(ministryService.getAll()));
        return ResponseEntity.ok(ministryService.getAll());
    }

    @GetMapping("/getUniversity")
    public ResponseEntity<List<UniversityEntity>> getAll(){
        return ResponseEntity.ok(ministryService.getAll());
    }

    @PostMapping("/validateStudentData")
    public ResponseEntity<ValidationResponseDto> validateStudentData(@RequestBody StudentRequestDto request) {
        ValidationResponseDto response = handleValidationRequest(request);
        return ResponseEntity.ok(response);
    }

    public ValidationResponseDto handleValidationRequest(StudentRequestDto request) {
        try {
            return ministryService.validateStudentData(request);
        } catch (ValidationException e) {
            return new ValidationResponseDto(false, e.getMessage());
        }
    }





}