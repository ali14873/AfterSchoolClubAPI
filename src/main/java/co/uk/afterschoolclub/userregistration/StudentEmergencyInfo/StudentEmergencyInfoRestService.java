package co.uk.afterschoolclub.userregistration.StudentEmergencyInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentEmergencyInfo")
public class StudentEmergencyInfoRestService {

    private final StudentEmergencyInfoApplicationService applicationService;

    @Autowired
    public StudentEmergencyInfoRestService(StudentEmergencyInfoApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/total-emergency-contacts")
    public ResponseEntity<Long> getTotalEmergencyContactCount() {
        long count = applicationService.getTotalEmergencyContactCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/students-with-emergency-contact")
    public ResponseEntity<Long> getStudentsWithEmergencyContactCount() {
        long count = applicationService.getStudentsWithEmergencyContactCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/students-with-multiple-emergency-contacts")
    public ResponseEntity<Long> getStudentsWithMultipleEmergencyContactsCount() {
        long count = applicationService.getStudentsWithMultipleEmergencyContactsCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/percentage-multiple-emergency-contacts")
    public ResponseEntity<Double> getPercentageOfStudentsWithMultipleEmergencyContacts() {
        double percentage = applicationService.getPercentageOfStudentsWithMultipleEmergencyContacts();
        return ResponseEntity.ok(percentage);
    }
}
