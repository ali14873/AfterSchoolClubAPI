package co.uk.afterschoolclub.userregistration.Allergy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/allergies")
public class AllergyRestService {
    private final AllergyApplicationService allergyApplicationService;

    @Autowired
    public AllergyRestService(AllergyApplicationService allergyApplicationService) {
        this.allergyApplicationService = allergyApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<AllergyDTO> createAllergy(@RequestBody AllergyDTO allergyDTO) {
        AllergyDTO createdAllergy = allergyApplicationService.createAllergy(allergyDTO);
        return new ResponseEntity<>(createdAllergy, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AllergyDTO>> getAllAllergies() {
        List<AllergyDTO> allergies = allergyApplicationService.getAllAllergies();
        return ResponseEntity.ok(allergies);
    }

    @GetMapping("/getByStudent/{studentId}")
    public ResponseEntity<List<AllergyDTO>> getAllergiesByStudentId(@PathVariable UUID studentId) {
        List<AllergyDTO> allergies = allergyApplicationService.getAllergiesByStudentId(studentId);
        return ResponseEntity.ok(allergies);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AllergyDTO> getAllergyById(@PathVariable UUID id) {
        AllergyDTO allergy = allergyApplicationService.getAllergyById(id);
        return ResponseEntity.ok(allergy);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AllergyDTO> updateAllergy(@PathVariable UUID id, @RequestBody AllergyDTO allergyDTO) {
        AllergyDTO updatedAllergy = allergyApplicationService.updateAllergy(id, allergyDTO);
        return ResponseEntity.ok(updatedAllergy);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAllergy(@PathVariable UUID id) {
        allergyApplicationService.deleteAllergy(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/countUniqueStudents")
    public ResponseEntity<Long> getCountUniqueStudentsWithAllergies() {
        return ResponseEntity.ok(allergyApplicationService.countUniqueStudentsWithAllergies());
    }

    @GetMapping("/countHighSeverity")
    public ResponseEntity<Long> getCountStudentsWithHighSeverityAllergies() {
        return ResponseEntity.ok(allergyApplicationService.countStudentsWithHighSeverityAllergies());
    }

    @GetMapping("/countMediumSeverity")
    public ResponseEntity<Long> getCountStudentsWithMediumSeverityAllergies() {
        return ResponseEntity.ok(allergyApplicationService.countStudentsWithMediumSeverityAllergies());
    }


}
