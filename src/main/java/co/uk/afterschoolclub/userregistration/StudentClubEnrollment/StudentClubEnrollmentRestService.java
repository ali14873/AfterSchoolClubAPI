package co.uk.afterschoolclub.userregistration.StudentClubEnrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentClubEnrollments")
public class StudentClubEnrollmentRestService {

    private final StudentClubEnrollmentApplicationService service;

    @Autowired
    public StudentClubEnrollmentRestService(StudentClubEnrollmentApplicationService service) {
        this.service = service;
    }

    @GetMapping("/students-without-clubs")
    public ResponseEntity<?> getStudentsWithoutClubs() {
        // Implement logic to get students without clubs
        return ResponseEntity.ok(service.countStudentsWithoutClubs());
    }

    @GetMapping("/students-enrolled-in-clubs")
    public ResponseEntity<?> getStudentsEnrolledInClubs() {
        // Implement logic to get students enrolled in clubs
        return ResponseEntity.ok(service.countStudentsEnrolledInClubs());
    }

    @GetMapping("/count-of-clubs")
    public ResponseEntity<?> getCountOfClubs() {
        // Implement logic to get count of clubs
        return ResponseEntity.ok(service.countOfClubs());
    }
}
