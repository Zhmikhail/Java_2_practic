package controlleruniversity;

import exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UniversityService;
import transport.dto.request.StudentRequestDto;
import transport.dto.response.ValidationResponseDto;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.logging.Level;


@RestController
@RequestMapping("external")
public class UniversityController {
    private static final Logger logger = Logger.getLogger(UniversityController.class.getName());
    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    public ValidationResponseDto processStudentData(StudentRequestDto request) throws ValidationException {
        return universityService.validateStudentData(request);
    }

    @PostMapping("/validateStudentData")
    public ResponseEntity<ValidationResponseDto> validateStudentData(@RequestBody StudentRequestDto request) {
        ValidationResponseDto response = handleValidationRequest(request);
        return ResponseEntity.ok(response);
    }

    public ValidationResponseDto handleValidationRequest(StudentRequestDto request) {
        return universityService.validateStudentData(request);
    }

    public void start(){
        System.out.println("University is started");
    }
}
