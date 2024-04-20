package co.uk.afterschoolclub.userregistration.Teacher;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teacher")
public class TeacherRestService {

    final TeacherApplicationService teacherApplicationService;

    @Autowired
    public TeacherRestService(TeacherApplicationService teacherApplicationService) {
        this.teacherApplicationService = teacherApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody final TeacherDTO request) {
        TeacherDTO teacher = teacherApplicationService.createTeacher(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teacherDTOList = teacherApplicationService.getAllTeachers();
        return ResponseEntity.status(HttpStatus.OK).body(teacherDTOList);
    }

    @GetMapping("/getTeacher/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable UUID id) {
        try {
            TeacherDTO teacherDTO = teacherApplicationService.getTeacherById(id);
            return ResponseEntity.status(HttpStatus.OK).body(teacherDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTeacherById(@PathVariable UUID id) {
        try {
            teacherApplicationService.deleteTeacherById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @PostMapping("/uploadCSV")
    public ResponseEntity<List<TeacherDTO>> uploadTeachersCSV(@RequestParam("File") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            List<TeacherDTO> createdTeachers = teacherApplicationService.processCSVFile(file.getInputStream());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTeachers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // In TeacherRestService.java

    @PutMapping("/update/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable UUID id, @RequestBody TeacherDTO teacherDTO) {
        try {
            TeacherDTO updatedTeacher = teacherApplicationService.updateTeacher(id, teacherDTO);
            return ResponseEntity.ok(updatedTeacher);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
