package co.uk.afterschoolclub.userregistration.Student;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentRestService {

    final StudentApplicationService studentApplicationService;

    @Autowired
    public StudentRestService(StudentApplicationService studentApplicationService) {
        this.studentApplicationService = studentApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody final StudentDTO request){
        StudentDTO student = studentApplicationService.createStudent(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getAll")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        List<StudentDTO> studentDTOList = studentApplicationService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(studentDTOList);
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable UUID id) {
        try {
            StudentDTO studentDTO = studentApplicationService.getStudentById(id);
            return ResponseEntity.status(HttpStatus.OK).body(studentDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable UUID id) {
        try {
            studentApplicationService.deleteStudentById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/uploadCSV")
    public ResponseEntity<List<StudentDTO>> uploadStudentsCSV(@RequestParam("File") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            List<StudentDTO> createdStudents = studentApplicationService.processCSVFile(file.getInputStream());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudents);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/countAll")
    public ResponseEntity<Long> countAllStudents(){
        Long count = studentApplicationService.countAllStudents();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/countActive")
    public ResponseEntity<Long> countActiveStudents(){
        Long count = studentApplicationService.countActiveStudents();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/countNextMonthBirthdays")
    public ResponseEntity<Long> countNextMonthBirthdays() {
        Long count = studentApplicationService.countNextMonthBirthdays();
        return ResponseEntity.ok(count);
    }





}
