//package co.uk.afterschoolclub.userregistration.StudentParent;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/studentParent")
//public class StudentParentRestService {
//
//    final StudentParentApplicationService studentParentApplicationService;
//
//    @Autowired
//    public StudentParentRestService(StudentParentApplicationService studentParentApplicationService) {
//        this.studentParentApplicationService = studentParentApplicationService;
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<StudentParentDTO> createStudentParent(@RequestBody final StudentParentDTO request) {
//        StudentParentDTO studentParent = studentParentApplicationService.createStudentParent(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(studentParent);
//    }
//
//    @GetMapping("/getChildrenByParent/{parentId}")
//    public ResponseEntity<List<StudentParentDTO>> getChildrenByParent(@PathVariable UUID parentId) {
//        try {
//            List<StudentParentDTO> children = studentParentApplicationService.getChildrenByParent(parentId);
//            return ResponseEntity.status(HttpStatus.OK).body(children);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//
//
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<StudentParentDTO> editStudentParent(@PathVariable UUID id, @RequestBody StudentParentDTO request) {
//        try {
//            StudentParentDTO updatedStudentParent = studentParentApplicationService.editStudentParent(id, request);
//            return ResponseEntity.ok(updatedStudentParent);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteStudentParent(@PathVariable UUID id) {
//        try {
//            studentParentApplicationService.deleteStudentParentById(id);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//}
